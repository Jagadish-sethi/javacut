package com.jagadish.concurrent;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class MyConcurrentHashMap<K,V>{
	
	
	final int segmentMask;
    final Segment<K,V>[] segments;
	
   
    public MyConcurrentHashMap(int concurrencylevel ) {
		// TODO Auto-generated constructor stub
    	segmentMask=concurrencylevel-1;
    	segments = new Segment[concurrencylevel];
    	
    	
	}
    
    public static void main(String[] args) {
		MyConcurrentHashMap<Integer, String>  map = new MyConcurrentHashMap<Integer, String>(4);
		System.out.println(map.put(1, "Jagan"));
		System.out.println(map.put(2, "Deban"));
		System.out.println(map.put(3, "Kisan"));
		System.out.println(map.put(4, "Jagadish"));
		System.out.println(map.put(1, "Jagan1"));
		
		System.out.println(map.get(1));
		System.out.println(map.get(2));
		System.out.println(map.get(3));
		System.out.println(map.get(4));
		
		
	}
    
    
    
    static final class Segment<K,V> extends ReentrantLock implements Serializable{
       
        
        static final int MAX_SCAN_RETRIES = 2;
        transient volatile HashEntry<K,V>[] table;
        transient int count;
        transient int modCount;
        
        Segment(HashEntry<K,V>[] tab) {
            this.table = tab;
        }

        final V put(K key, int hash, V value, boolean onlyIfAbsent) {
            HashEntry<K,V> node = tryLock() ? null :
                scanAndLockForPut(key, hash, value);
            V oldValue;
            try {
                HashEntry<K,V>[] tab = table;
                int index = (tab.length - 1) & hash;
                HashEntry<K,V> first = tab[index];
                for (HashEntry<K,V> e = first;;) {
                    if (e != null) {
                        K k;
                        if ((k = e.key) == key ||
                            (e.hash == hash && key.equals(k))) {
                            oldValue = e.value;
                            if (!onlyIfAbsent) {
                                e.value = value;
                                ++modCount;
                            }
                            break;
                        }
                        e = e.next;
                    }
                    else {
                        if (node != null)
                            node.next = first;
                        else
                            node = new HashEntry<K,V>(hash, key, value, first);
                        int c = count + 1;
                        tab[index]= node;
                        ++modCount;
                        count = c;
                        oldValue = null;
                        break;
                    }
                }
            } finally {
                unlock();
            }
            return oldValue;
        }

        

        private HashEntry<K,V> scanAndLockForPut(K key, int hash, V value) {
            HashEntry<K,V> first = entryForHash(this, hash);
            HashEntry<K,V> e = first;
            HashEntry<K,V> node = null;
            int retries = -1; // negative while locating node
            while (!tryLock()) {
                HashEntry<K,V> f; // to recheck first below
                if (retries < 0) {
                    if (e == null) {
                        if (node == null) // speculatively create node
                            node = new HashEntry<K,V>(hash, key, value, null);
                        retries = 0;
                    }
                    else if (key.equals(e.key))
                        retries = 0;
                    else
                        e = e.next;
                }
                else if (++retries > MAX_SCAN_RETRIES) {
                    lock();
                    break;
                }
                else if ((retries & 1) == 0 &&
                         (f = entryForHash(this, hash)) != first) {
                    e = first = f; // re-traverse if entry changed
                    retries = -1;
                }
            }
            return node;
        }

        private HashEntry<K, V> entryForHash(Segment<K, V> segment, int hash) {
			int i = hash&(segment.table.length-1);
			return segment.table[i];
		}

		/**
         * Scans for a node containing the given key while trying to
         * acquire lock for a remove or replace operation. Upon
         * return, guarantees that lock is held.  Note that we must
         * lock even if the key is not found, to ensure sequential
         * consistency of updates.
         */
        private void scanAndLock(Object key, int hash) {
            // similar to but simpler than scanAndLockForPut
            HashEntry<K,V> first = entryForHash(this, hash);
            HashEntry<K,V> e = first;
            int retries = -1;
            while (!tryLock()) {
                HashEntry<K,V> f;
                if (retries < 0) {
                    if (e == null || key.equals(e.key))
                        retries = 0;
                    else
                        e = e.next;
                }
                else if (++retries > MAX_SCAN_RETRIES) {
                    lock();
                    break;
                }
                else if ((retries & 1) == 0 &&
                         (f = entryForHash(this, hash)) != first) {
                    e = first = f;
                    retries = -1;
                }
            }
        }

        /**
         * Remove; match on key only if value null, else match both.
         */
        final V remove(Object key, int hash, Object value) {
            if (!tryLock())
                scanAndLock(key, hash);
            V oldValue = null;
            try {
                HashEntry<K,V>[] tab = table;
                int index = (tab.length - 1) & hash;
                HashEntry<K,V> e = tab[index];
                HashEntry<K,V> pred = null;
                while (e != null) {
                    K k;
                    HashEntry<K,V> next = e.next;
                    if ((k = e.key) == key ||
                        (e.hash == hash && key.equals(k))) {
                        V v = e.value;
                        if (value == null || value == v || value.equals(v)) {
                            if (pred == null)
                                tab[index]=next;
                            else
                                pred.next=next;
                            ++modCount;
                            --count;
                            oldValue = v;
                        }
                        break;
                    }
                    pred = e;
                    e = next;
                }
            } finally {
                unlock();
            }
            return oldValue;
        }

        final boolean replace(K key, int hash, V oldValue, V newValue) {
            if (!tryLock())
                scanAndLock(key, hash);
            boolean replaced = false;
            try {
                HashEntry<K,V> e;
                for (e = entryForHash(this, hash); e != null; e = e.next) {
                    K k;
                    if ((k = e.key) == key ||
                        (e.hash == hash && key.equals(k))) {
                        if (oldValue.equals(e.value)) {
                            e.value = newValue;
                            ++modCount;
                            replaced = true;
                        }
                        break;
                    }
                }
            } finally {
                unlock();
            }
            return replaced;
        }

        final V replace(K key, int hash, V value) {
            if (!tryLock())
                scanAndLock(key, hash);
            V oldValue = null;
            try {
                HashEntry<K,V> e;
                for (e = entryForHash(this, hash); e != null; e = e.next) {
                    K k;
                    if ((k = e.key) == key ||
                        (e.hash == hash && key.equals(k))) {
                        oldValue = e.value;
                        e.value = value;
                        ++modCount;
                        break;
                    }
                }
            } finally {
                unlock();
            }
            return oldValue;
        }

        final void clear() {
            lock();
            try {
                HashEntry<K,V>[] tab = table;
                for (int i = 0; i < tab.length ; i++)
                    tab[i]=null;
                ++modCount;
                count = 0;
            } finally {
                unlock();
            }
        }
    }

    
    static final class HashEntry<K,V> {
        final int hash;
        final K key;
        volatile V value;
        volatile HashEntry<K,V> next;

        HashEntry(int hash, K key, V value, HashEntry<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


	private int hash(Object k) {
        int h = k.hashCode();
        h += (h <<  15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h <<   3);
        h ^= (h >>>  6);
        h += (h <<   2) + (h << 14);
        return h ^ (h >>> 16);
    }
	
    public V get(Object key) {
        int hash = hash(key);
        int i = (hash >>> 2) & segmentMask;
        Segment s = segments[i];
        
        	HashEntry<K,V> current = s.table[hash&(s.table.length-1)];
        	while(current!=null){
        		if(current.key == key || current.key.equals(key)){
        			return current.value;
        		}
        	}
		
        
        return null;
    }

    

    

    
    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        
        if (value == null)
            throw new NullPointerException();
        int hash = hash(key);
        int i = (hash >>> 2) & segmentMask;
        if(segments[i]==null){
        	HashEntry<K, V>[] tables = new HashEntry[16];
        	segments[i] = new Segment<K,V>(tables);
        }
        Segment s = segments[i];
        return (V) s.put(key, hash, value, false);
    }

    @SuppressWarnings("unchecked")
    public V putIfAbsent(K key, V value) {
    	if (value == null)
            throw new NullPointerException();
        int hash = hash(key);
        int i = (hash >>> 2) & segmentMask;
        if(segments[i]==null){
        	HashEntry<K, V>[] tables = new HashEntry[16];
        	segments[i] = new Segment<K,V>(tables);
        }
        Segment s = segments[i];
        return (V) s.put(key, hash, value, false);
    }
	

}
