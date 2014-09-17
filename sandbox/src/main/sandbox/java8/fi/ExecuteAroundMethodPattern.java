package sandbox.java8.fi;

import java.util.function.Consumer;
import static java.lang.System.out;
 
class ExecuteAroundMethodPattern {
    private ExecuteAroundMethodPattern() {out.println("created...");}
    public void operation1() {out.println("operation 1");}
    public void operation2() {out.println("operation 2");}
    public void operation3(String s) {out.println("operation 3:" + s);}
    public String operation4(String s) {return "aaaa"+s;}
    private void close() { out.println("cleanup");}
 
    public static void use(Consumer<ExecuteAroundMethodPattern> block) {
        ExecuteAroundMethodPattern resource = new ExecuteAroundMethodPattern();
        try {
            block.accept(resource);
            out.println("*");
        } finally {
            resource.close();
        }
    }
    
    public static void main(String... args) {
    	final String s=null;
        ExecuteAroundMethodPattern.use(resource -> {
            resource.operation1();
            resource.operation2();
            resource.operation3("judihui");
            resource.operation4("bbbb");
        });
    }
}