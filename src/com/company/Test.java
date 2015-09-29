package com.company;

public class Test {

    public static void main(String[] args) {
        PQueue<String> queue = new PQueue<>();
//
//        try {
//            queue.pop();
//        } catch (EmptyQueueException ex) {
//            ex.printStackTrace();
//        }
//
//        queue.push("foo");
//        queue.push("bar");
//        assert(queue.size() == 2);
//        assert(queue.top().equals("foo"));
//        queue.pop();
//        assert(queue.size() == 1);
//        assert(queue.top().equals("bar"));
//        queue.clear();
//        assert(queue.size() == 0);

        PQueue<Integer> s = new PQueue<Integer>();
        s.push(1); s.push(2); s.push(3); s.push(4);
        for (Integer i : s) {
            System.out.println(i);
        }
    }
}
