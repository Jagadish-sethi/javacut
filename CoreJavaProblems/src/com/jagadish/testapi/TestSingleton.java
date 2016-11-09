package com.jagadish.testapi;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ReflectPermission;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Singleton implements Serializable{
    private static final long serialVersionUID = 3119105548371608200L;
    private static Singleton singleton = new Singleton();
    private Singleton() {
    	if( Singleton.singleton != null ) {
            throw new InstantiationError( "Creating of this object is not allowed." );
        }{
    	
        }
    }
    public static Singleton getInstance(){
    	
    	
        return singleton;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning of this class is not allowed"); 
    }
    protected Object readResolve() {
        return singleton;
    }
    
    
    
   
}


class EagerInitializedSingleton {
    

    
    
    
    private EagerInitializedSingleton() throws Exception {

           
           
    }
    
    private static class SingletonHelper{
    
           private static  EagerInitializedSingleton INSTANCE=null;
           static{

    try {
           INSTANCE = new EagerInitializedSingleton();
    } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
    }
           }
           
     
 }
 
 public static EagerInitializedSingleton getInstance(){
     return SingletonHelper.INSTANCE;
 }

}



public class TestSingleton {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			//Singleton obj = Singleton.class.newInstance();
	    	//System.out.println(obj);
	    	
	    	/*Constructor con =Singleton.class.getDeclaredConstructors()[0];
	    	
	    	con.setAccessible(true);
	    	Singleton obj1=(Singleton)con.newInstance(null);
	    	System.out.println(obj1);*/
		
		//Singleton.class.
		
		//Singleton.getInstance();
		
		/*Constructor con =EagerInitializedSingleton.class.getDeclaredConstructors()[0];
    	
    	con.setAccessible(true);
    	EagerInitializedSingleton obj1=(EagerInitializedSingleton)con.newInstance(null);
    	System.out.println(obj1);*/
		
		ExecutorService exc = Executors.newCachedThreadPool();
        final HashMap<Integer, Integer> map0 = new HashMap<>();
        
        final Map<Integer, Integer> map = Collections.synchronizedMap(map0);
        
        for(int i=0;i<100000;i++){
               exc.submit(new Runnable() {

                     @Override
                     public void run() {
                            while(true){
                                   try {
                                          Thread.sleep(10);
                                   } catch (InterruptedException e) {
                                          e.printStackTrace();
                                   }
                                   map.put(Singleton.getInstance().hashCode(), 1);
                                   //EagerInitializedSingleton.getInstance());
                                   if(map.size() != 1){
                                          System.out.println("error"+map.size());
                                          System.out.println(map);
                                   }else {
                                	   System.out.println("working");
                                   }
                            }
                     }
               });

        }
        
        exc.shutdown();
        try {
               exc.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
               e.printStackTrace();
        }

	    	
	}
	
}