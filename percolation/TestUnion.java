import java.util.Arrays;
class TestUnion {
    
    public WeightedQuickUnionUF wuf;
    
    TestUnion() {
        wuf = new WeightedQuickUnionUF(4);
    }
    
    public static void main (String [] args){
        TestUnion tu = new TestUnion();
        
        System.out.println(tu.wuf.connected(0,0));
        System.out.println(tu.wuf.connected(0,1));
        System.out.println(tu.wuf.connected(1,0));
        System.out.println(tu.wuf.connected(1,1));
        System.out.println(tu.wuf.connected(1,2));
        System.out.println(tu.wuf.connected(0,3));
        
        tu.wuf.union(1,0);
        
        System.out.println();
        System.out.println(tu.wuf.connected(0,0));
        System.out.println(tu.wuf.connected(0,1));
        System.out.println(tu.wuf.connected(1,0));
        System.out.println(tu.wuf.connected(1,1));
        System.out.println(tu.wuf.connected(1,2));
    }
    
}