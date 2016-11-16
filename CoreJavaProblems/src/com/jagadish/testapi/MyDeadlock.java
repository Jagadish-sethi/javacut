package com.jagadish.testapi;

public class MyDeadlock {
	 
    String str1 = "Java";
    String str2 = "UNIX";
     
    Thread trd1 = new Thread("My Thread 1"){
        public void run(){
            while(true){
                synchronized(str1){
                    synchronized(str2){
                        System.out.println(str1 + str2);
                    }
                }
            }
        }
    };
     
    Thread trd2 = new Thread("My Thread 2"){
        public void run(){
            while(true){
                synchronized(str2){
                    synchronized(str1){
                        System.out.println(str2 + str1);
                    }
                }
            }
        }
    };
     
    public static void main(String a[]){
        /*MyDeadlock mdl = new MyDeadlock();
        mdl.trd1.start();
        mdl.trd2.start();*/
    	
    	MyDeadlock1 mdl = new MyDeadlock1();
        mdl.trd1.start();
        mdl.trd2.start();
        
        mdl.trd2.interrupt();
    }
}


class MyDeadlock1 {
	 
    String str1 = "Java";
     
    Thread trd1 = new Thread("My Thread 1"){
        public void run(){
            while(true){
            	System.out.println("thread 1");
                
                synchronized(str1){
                	System.out.println("thread 1");
                    while(true);
                }
            }
        }
    };
     
    Thread trd2 = new Thread("My Thread 2"){
        public void run(){
            while(true){
            	System.out.println("thread 2");
                synchronized(str1){
                	System.out.println("thread 2 2");
                        
                }
            }
        }
    };
     
    public static void main(String a[]){
        MyDeadlock mdl = new MyDeadlock();
        mdl.trd1.start();
        mdl.trd2.start();
    }
}
