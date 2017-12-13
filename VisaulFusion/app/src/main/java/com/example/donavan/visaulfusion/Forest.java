package com.example.donavan.visaulfusion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 11/04/2017.
 */

public class Forest
{
    private List<Tree> trees;

    public Forest()
    {
        trees = new ArrayList<>(); //diamond syntax Java 7, ArrayList<Tree>() on Java 6
    }

    public void addTree(Tree tree)
    {
        trees.add(tree);
    }

    public void growTreesByOneYear()
    {
        for(Tree tree : trees)
        {
            tree.growOneYear();
        }

        //you can do trees.stream().forEach(x -> x.growOneYear()); on Java 8
    }

    public void showTrees()
    {
        for(Tree tree : trees)
        {
            tree.show();
        }
    }
}
