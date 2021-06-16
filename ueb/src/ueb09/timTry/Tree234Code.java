package ueb09.timTry;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Tree234Code {
    public static void main(String[] args) throws Exception {
        Tree234<Integer> tree = new Tree234<>();
        tree.insert(new DataItem<>(13));
        tree.insert(new DataItem<>(24));
        tree.insert(new DataItem<>(34));
        tree.insert(new DataItem<>(23));
        tree.insert(new DataItem<>(98));
        tree.insert(new DataItem<>(45));
        tree.insert(new DataItem<>(75));
        tree.insert(new DataItem<>(46));
        tree.insert(new DataItem<>(30));
        tree.insert(new DataItem<>(12));
        tree.insert(new DataItem<>(2));

        tree.levelTraverse();

//        /13/24/45
//        /2/12
//        /23
//        /30/34
//        /46/75/98
    }
}


