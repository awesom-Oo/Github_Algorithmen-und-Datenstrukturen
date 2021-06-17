package ueb09;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TwoThreeFourTreeTest {

    TwoThreeFourTree<Integer> tree;

    @BeforeAll
    public void SetUp() {
        tree = new TwoThreeFourTree<>();
        tree.add(4);
        tree.add(11);
    }

    @Test
    public void Test234Tree() {


    }
}