/**
 * Created by xpf on 2018/6/27 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class QuickSort {

    /**
     * 快速排序是我们之前学习的冒泡排序的升级，他们都属于交换类排序，都是采用不断的比较和移动来实现排序的。
     * 快速排序是一种非常高效的排序算法，它的实现，增大了记录的比较和移动的距离，将关键字较大的记录从前面
     * 直接移动到后面，关键字较小的记录从后面直接移动到前面，从而减少了总的比较次数和移动次数。同时采用
     * “分而治之”的思想，把大的拆分为小的，小的拆分为更小的，其原理如下：对于给定的一组记录，选择一个基准
     * 元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分
     * 大于等于基准元素,此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分，直到
     * 序列中的所有记录均有序为止。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {8, 19, 5, 13, 7, 1, 99, 38, 520, 1314};
        int start = 0;
        int end = array.length - 1;
        fastSort(array, start, end);
        for (int i : array) {
            System.out.print(i + ",");
        }
    }

    /**
     * fast sort algorithm
     *
     * @param array
     * @param low
     * @param high
     */
    public static void fastSort(int[] array, int low, int high) {
        int start = low;
        int end = high;
        int key = array[low];

        while (end > start) {

            // 从后往前比较
            while (end > start && array[end] >= key) {
                end--;
            }

            if (array[end] <= key) {
                int temp = array[end];
                array[end] = array[start];
                array[start] = temp;
            }

            // 从前往后比较
            while (end > start && array[start] <= key) {
                start++;
            }

            if (array[start] >= key) {
                int temp = array[start];
                array[start] = array[end];
                array[end] = temp;
            }

            // 此时第一次循环比较结束，关键值的位置已经确定了，左边的值都比关键值小，右边的值都比关键值大，
            // 但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }

        // 递归调用
        if (start > low) {
            // 左边序列，第一个索引位置到关键值索引 -1
            fastSort(array, low, start - 1);
        }

        if (end < high) {
            // 右边序列，从关键值索引 +1 到最后一个
            fastSort(array, end + 1, high);
        }
    }

    /**
     * 快速排序的优化
     * 对于基准位置的选取一般有三种方法：固定切分，随机切分和三取样切分。
     * 固定切分的效率并不是太好，随机切分是常用的一种切分，效率比较高，
     * 最坏情况下时间复杂度有可能为O(N2).对于三数取中选择基准点是最理想的一种。
     * @param array
     * @param lo
     * @param hi
     * @return
     */
    public static int partition(int[] array, int lo, int hi) {
        // 三数取中
        int mid = lo + (hi - lo) / 2;
        if (array[mid] > array[hi]) {
            swap(array[mid], array[hi]);
        }
        if (array[lo] > array[hi]) {
            swap(array[lo], array[hi]);
        }
        if (array[mid] > array[lo]) {
            swap(array[mid], array[lo]);
        }
        int key = array[lo];

        while (lo < hi) {
            while (array[hi] >= key && hi > lo) {
                hi--;
            }
            array[lo] = array[hi];
            while (array[lo] <= key && hi > lo) {
                lo++;
            }
            array[hi] = array[lo];
        }
        array[hi] = key;
        return hi;
    }

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    public static void sort(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int index = partition(array, lo, hi);
        sort(array, lo, index - 1);
        sort(array, index + 1, hi);
    }
}
