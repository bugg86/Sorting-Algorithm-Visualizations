package InsertionSort;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class SortArray {

    private int[] array;
    private int arrayLength;
    private Rectangle[] rectangles;
    private Random rand = new Random();

    //SortArray constructor initializes the array.
    public SortArray(int[] array, Rectangle[] rectangles) {
        this.array = array;
        this.rectangles = rectangles;
    }

    //Display the Rectangles.
    public void makeRectangles() {
        //Initialize the Rectangles array
        rectangles = new Rectangle[array.length];
        //Iterate through the array and create the rectangles
        for (int i = 0; i < array.length; i++) {
            rectangles[i] = new Rectangle();
            //Sets the upper left corner's x coordinate.
            rectangles[i].setX(i * 15);
            //Set the width of the rectangle.
            rectangles[i].setWidth(15);
            //Sets the upper left corner's y coordinate
            rectangles[i].setY(0);
            //Set the height of the Rectangle to the respective array value.
            rectangles[i].setHeight(array[i]);
            //Fill in the rectangle.
            rectangles[i].setFill(Color.BLACK);
        }
    }

    //Remakes the rectangles to account for the swap that has occurred.
    public void remakeRectangles(int firstIndex, int secondIndex, int keyIndex) {
        //This loop functions identically to the one in the makeRectangles method.
        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i].setX(i * 15);
            rectangles[i].setWidth(15);
            rectangles[i].setY(0);
            rectangles[i].setHeight(array[i]);
            rectangles[i].setFill(Color.BLACK);
        }
        //Fill in the swapped rectangles red.
        rectangles[firstIndex].setFill(Color.RED);
        rectangles[secondIndex].setFill(Color.RED);
        rectangles[keyIndex].setFill(Color.GREEN);
    }

    //This is the method that does the sorting.
    public void insertionSort() {
        //Declare and initialize the variable that tracks the number of swaps.
        AtomicInteger numOfSwaps = new AtomicInteger();

        //Create a new thread for the sorting algorithm to run on.
        new Thread(() -> {
            /*do {
                //Set the number of swaps to zero at the beginning of each array iteration.
                numOfSwaps.set(0);
                try {
                    //Iterate through entire array
                    for (int i = 0; i < array.length - 1; i++) {
                        //Check if a swap needs to occur
                        if (array[i] > array[i + 1]) {
                            //Swap
                            swap(i, i + 1);
                            numOfSwaps.getAndIncrement();
                            //Pause the thread
                            Thread.sleep(50);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (numOfSwaps.get() != 0);*/
            try {
                for(int i = 0; i < array.length; i++) {
                    //Store the currentInt and initialize j.
                    int currentInt = array[i];
                    int j = i - 1;

                    //Move all elements greater than the currentInt up on position, to make room for the currentInt.
                    while (j >= 0 && array[j] > currentInt) {
                        //Call the swap method
                        swap(j + 1, j, i);
                        //Iterate towards the front of the array
                        j--;
                        //Pause the thread
                        Thread.sleep(50);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Make the first 2 rectangles black so they look the same as the other rectangles.
            rectangles[0].setFill(Color.BLACK);
            rectangles[1].setFill(Color.BLACK);
        }).start();
    }

    //Swap method
    public void swap(int firstIndex, int secondIndex, int keyIndex) {
        //Swap the array values
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        //Swap the rectangles
        Rectangle tempRectangle = rectangles[firstIndex];
        rectangles[firstIndex] = rectangles[secondIndex];
        rectangles[secondIndex] = tempRectangle;

        //Repaint/remake the Rectangles to update if a swap if one occurred.
        remakeRectangles(firstIndex, secondIndex, keyIndex);
    }

    //Print out the array to the console.
    public void printArray() {
        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    //Return the length of the array
    public int getLength() {
        return arrayLength;
    }

    //Return the array of Rectangles
    public Rectangle[] getRectangles() {
        return rectangles;
    }
}
