/**
 * Created by xpf on 2018/6/21 :)
 * GitHub:xinpengfei520
 * Function:
 */
public class SortUtils {

	public static void main(String[] args) {
		int[] array = {98,21,6,10,9,3,12,1,18,0,14,65,78,45,520,1314};

		int[] array1 = bubbleSort(array);
		for(int i:array1){
			System.out.print(i+",");
		}
		System.out.print("\n");
		
		int[] array2 = flagBubbleSort(array);
		for(int i:array2){
			System.out.print(i+",");
		}
		System.out.print("\n");
		
		int[] array3 = tailFlagBubbleSort(array);
		for(int i:array3){
			System.out.print(i+",");
		}
		System.out.print("\n");
	}

    /**
     * normal bubble sort
     *
     * @param array no sort array
     */
    public static int[] bubbleSort(int[] array) {
        int arrayLen = array.length;
        // 外层 for 循环表示遍历的趟数
        for (int i = 0; i < arrayLen; i++) {
            /*
             * 内层 for 循环表示遍历未排序数的个数
             * 第一个遍历的个数为整个数组长度，之后每次遍历都会少一个
             */
            for (int j = 1; j < arrayLen - i; j++) {
                /*
                 * 因为 j 从 1 开始，所以数组第一个数为 array[j - 1]
                 * 与相邻的后一个数进行比较，把较大的数进行交换的往后移动
                 */
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        return array;
    }

    /**
     * flag bubble sort
     *
     * @param array no sort array
     */
    public static int[] flagBubbleSort(int[] array) {
        int arrayLen = array.length;
        boolean flag = true; // 记录是否发生交换的标志，默认为 true
        while (flag) {
            flag = false; // 每次开始排序前设置 flag 为未排序过
            for (int j = 1; j < arrayLen; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    flag = true; // 表示交换过数
                }
            }

            arrayLen--; // 缩小一次排序的尾边界
        }

        return array;
    }

    /**
     * tail flag bubble sort
     *
     * @param array no sort array
     */
    public static int[] tailFlagBubbleSort(int[] array) {
        int tailLen;
        int tailFlag = array.length; // flag 来记录最后交换的位置，也就是排序的尾边界

        while (tailFlag > 0) { // 排序未结束标志
            tailLen = tailFlag; // tailLen 来记录遍历的尾边界
            tailFlag = 0;

            for (int j = 1; j < tailLen; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;

                    // 表示交换过数据
                    tailFlag = j; // 记录最新的尾边界
                }
            }
        }

        return array;
    }
}
