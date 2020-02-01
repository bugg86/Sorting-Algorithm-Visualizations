package BubbleSort;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class SortArray {

    private int[] array;
    private int arrayLength;
    private Rectangle[] rectangles;
    private Random rand = new Random();

    public SortArray() {
        array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(1001);
        }
        arrayLength = array.length;
    }

    public void makeRectangles() {
        rectangles = new Rectangle[array.length];
        for (int i = 0; i < array.length; i++) {
            rectangles[i] = new Rectangle();
            rectangles[i].setX(i * 15);
            rectangles[i].setWidth(15);
            rectangles[i].setY(0);
            rectangles[i].setHeight(array[i]);
            rectangles[i].setFill(Color.BLACK);
        }
    }

    public void remakeRectangles(int firstIndex, int secondIndex) {
        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i].setX(i * 15);
            rectangles[i].setWidth(15);
            rectangles[i].setY(0);
            rectangles[i].setHeight(array[i]);
            rectangles[i].setFill(Color.BLACK);
        }
        rectangles[firstIndex].setFill(Color.RED);
        rectangles[secondIndex].setFill(Color.RED);
    }

    public void sort() {
        AtomicInteger numOfSwaps = new AtomicInteger();

        new Thread(() -> {
            do {
                numOfSwaps.set(0);
                try {
                    for (int i = 0; i < array.length - 1; i++) {
                        if (array[i] > array[i + 1]) {
                            swap(i, i + 1);
                            numOfSwaps.getAndIncrement();
                            Thread.sleep(50);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (numOfSwaps.get() != 0);
            rectangles[0].setFill(Color.BLACK);
            rectangles[1].setFill(Color.BLACK);
        }).start();
    }

    public void swap(int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        Rectangle tempRectangle = rectangles[firstIndex];
        rectangles[firstIndex] = rectangles[secondIndex];
        rectangles[secondIndex] = tempRectangle;

        remakeRectangles(firstIndex, secondIndex);
    }

    public void printArray() {
        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public int getLength() {
        return arrayLength;
    }

    public Rectangle[] getRectangles() {
        return rectangles;
    }

    public static void sleepFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
