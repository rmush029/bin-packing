package com.chetty.binpacking;

import com.chetty.binpacking.data.Container;
import com.chetty.binpacking.datastructures.BinarySearchTree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Babji P, Chetty
 */
public class BinPacking {
    /**
     *
     * @param quantity
     * @param availableContainers
     * @return
     */
    public ArrayList<Container> packIt(int[] quantityArray, List<Container> availableContainers) {
        ArrayList<Container> bestFitContainerList = null;

        if(availableContainers != null && !availableContainers.isEmpty()) {
            BinarySearchTree bst = createBalancedBST(availableContainers);
            bestFitContainerList = getBestFitContainers(quantityArray, bst);
        }

        return bestFitContainerList;
    }

    /**
     *
     * @param availableContainers
     * @return
     */
    private BinarySearchTree createBalancedBST(List<Container> availableContainers) {
        BinarySearchTree bst = null;

        if(availableContainers != null && !availableContainers.isEmpty()) {
            Collections.sort(availableContainers);
            int size = availableContainers.size();
            int rootIndex = size/2;

            bst = new BinarySearchTree(availableContainers.remove(rootIndex));

            if(!availableContainers.isEmpty()) {
                for(Container container: availableContainers) {
                    bst.add(container);
                }
            }
        }

        return bst;
    }

    /**
     *
     * @param quantity
     * @param bst
     * @return
     */
    private ArrayList<Container> getBestFitContainers(int[] quantityArray, BinarySearchTree bst) {
        ArrayList<Container> bestFitContainerList = new ArrayList<Container>();

        for(int i=0;i<quantityArray.length;i++) {
            int remainingQuantity = quantityArray[i];

            while(remainingQuantity > 0) {
                Container searchObj = new Container(remainingQuantity);
                System.out.println("Remaining : " + remainingQuantity);

                Container bestFitContainer = (Container) bst.ceiling(searchObj);
                if(bestFitContainer == null) {
                    System.out.println("Here");
                    bestFitContainer = (Container) bst.lower(searchObj);
                }

                if(bestFitContainer != null) {
                    int capacity = bestFitContainer.getCapacity();
                    bst.remove(bestFitContainer);                    
                    bestFitContainer.setUsedCapacity((remainingQuantity >= capacity) ? capacity
                                                                                     : remainingQuantity);
                    bestFitContainerList.add(bestFitContainer);
                    if(bestFitContainer.getCapacity() > 0) {
                        bst.add(bestFitContainer);
                    }
                    remainingQuantity = remainingQuantity - capacity;
                } else {
                    break;
                }
            }
        }

        return bestFitContainerList;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Enter Number Of Available Containers : ");
        Scanner scanner = new Scanner(System.in);

        int numOfContainers = scanner.nextInt();

        System.out.println("Enter Container Capacities : ");
        List<Container> availableContainers = new ArrayList<Container>();
        for(int i=0;i<numOfContainers;i++) {
            availableContainers.add(new Container(scanner.nextInt()));
        }

        System.out.println("Enter the number of items to be packed in the containers : ");
        int numOfItems = scanner.nextInt();

        System.out.println("Enter Item Quantities : ");
        int[] quantityArray = new int[numOfItems];
        for(int i=0;i<numOfItems;i++) {
            quantityArray[i] = scanner.nextInt();
        }

        BinPacking binPacking = new BinPacking();
        ArrayList<Container> bestFitContainerList = binPacking.packIt(quantityArray, availableContainers);

        if(bestFitContainerList != null && !bestFitContainerList.isEmpty()) {
            int index = 1;
            for(Container container: bestFitContainerList) {
                System.out.println("Best Fit Container " + index++ + " : Actual Capacity - " +
                        container.getActualCapacity() + " Used Capacity : " + container.getUsedCapacity());
            }
        }
    }
}