package com.zhouhc.datastruct.mergingSort;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;

/**
 * 归并排序: 同样也是使用完全二叉树的排序
 * 不过位置跳跃比较严重，因此归并排序出来了，
 * 归并排序使用的就是将数组递归分割成单位长度为1的
 * 小的数组，对着两个数组进行排序，
 * 然后沿路递归归去，对排好序的数组，进行两两合并，直到出现一个数组为止。
 */
public class MergingSort {

    //递归版本的归并排序
    public static Integer[] rSort(Integer[] sortingArrys) {
        rMsort(sortingArrys, new Integer[sortingArrys.length], 0, sortingArrys.length - 1);
        return sortingArrys;
    }

    //递归排序
    private static void rMsort(Integer[] sortingArrys, Integer[] tempArrays, Integer low, Integer high) {
        //创建数组
        if (low >= high) {
            tempArrays[low] = sortingArrys[low];
        } else {
            //中间值
            int middle = (low + high) / 2;
            //原数组进行分割，一致分割到只有一个元素为止
            rMsort(sortingArrys, tempArrays, low, middle);
            rMsort(sortingArrys, tempArrays, middle + 1, high);
            //因为左右只有一个，所以对这两个进行一一合并
            Merge(tempArrays, sortingArrys, low, middle, high);
        }
    }

    //数组的合并方法
    private static void Merge(Integer[] tempArrays, Integer[] sortingArrays, Integer low, Integer middle, Integer high) {
        //备份用处
        int middleBak = middle + 1;
        int index = low;
        //备份数据
        int start = low;
        int end = high;
        //开始进行两两合并,先进行两个比较
        while ((low <= middle) && (middleBak <= high)) {
            //两个数组分别取最小的数近行合并
            if (tempArrays[low] > tempArrays[middleBak])
                sortingArrays[index++] = tempArrays[middleBak++];
            else
                sortingArrays[index++] = tempArrays[low++];
        }
        //将两个数组中剩余数组直接进行合并
        while (low <= middle)
            sortingArrays[index++] = tempArrays[low++];
        while (middleBak <= high)
            sortingArrays[index++] = tempArrays[middleBak++];

        //保整tempArrays数组也是有序的
        while (start <= end)
            tempArrays[start] = sortingArrays[start++];
    }

    //非递归版本的排序,比较好理解的版本，是维基百科上面的
    public static void merge_sort(int[] arr) {
        int[] orderedArr = new int[arr.length];
        //表示剩余的序列，只有剩余1个序列才算归并完成，i可以表示每次归并的后的数组的大小，从2,4,8,16...
        for (int i = 2; i < arr.length * 2; i *= 2) {
            //表示每次对于当前归并数组的大小， 总共要合并多少次 (arr.length - 1  + i ) / i
            for (int j = 0; j < (arr.length + i - 1) / i; j++) {
                int left = i * j;
                //越界判断
                int mid = left + i / 2 >= arr.length ? (arr.length - 1) : (left + i / 2);
                //越界判断
                int right = i * (j + 1) - 1 >= arr.length ? (arr.length - 1) : (i * (j + 1) - 1);
                int start = left, l = left, m = mid;
                while (l < mid && m <= right) {
                    if (arr[l] < arr[m]) {
                        orderedArr[start++] = arr[l++];
                    } else {
                        orderedArr[start++] = arr[m++];
                    }
                }
                while (l < mid)
                    orderedArr[start++] = arr[l++];
                while (m <= right)
                    orderedArr[start++] = arr[m++];
                System.arraycopy(orderedArr, left, arr, left, right - left + 1);
            }
        }
    }

    //递归版本的排序,比较好理解的版本，是维基百科上面的
    private static void merge_sort_recursive(int[] arr, int[] result, int start, int end) {
        if (start >= end)
            return;
        int len = end - start, mid = (len >> 1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        merge_sort_recursive(arr, result, start1, end1);
        merge_sort_recursive(arr, result, start2, end2);
        int k = start;
        while (start1 <= end1 && start2 <= end2)
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        while (start1 <= end1)
            result[k++] = arr[start1++];
        while (start2 <= end2)
            result[k++] = arr[start2++];
        for (k = start; k <= end; k++)
            arr[k] = result[k];
    }

    //非递归版本的排序
    public static Integer[] iSort(Integer[] sortingArrys) {
        Integer[] resultArrays = Arrays.copyOf(sortingArrys, sortingArrys.length);
        //开始进行排序
        //从1开始，进行两两排序然后在合并，增加幅度
        int step = 1;
        //进行循环,只要增加幅度小于长度那就需要排序
        while (step < resultArrays.length) {
            //调用方法
            iMsort(sortingArrys, resultArrays, step, resultArrays.length);
            step *= 2;
        }
        return resultArrays;
    }

    private static void iMsort(Integer[] sortingArrys, Integer[] resultArrays, Integer step, Integer length) {
        int start = 0;
        /**
         * 循环结束条件,因为进行两两归并，我们假设是能进行刚刚好能进行两两归并，start坐标的最大值就是length- 2*step,
         * 如果不能进行两两归并，最大值一定要比length- 2*step小。 比如说，长度为10的最大归并开始下标为8(也就是8和9归并),
         * 长度为9的最大开始归并下标为7(也就是8会被剩下来)，
         */
        while (start <= length - 2 * step) {
            //两两合并
            Merge(resultArrays, sortingArrys, start, start + step - 1, start + 2 * step - 1);
            //下一个开始和结束的方式
            start = start + 2 * step;
        }

        /**
         * 处理最后两个子序列问题，如果是两个子序列,只要start小于length-step+1就证明还有两个序列没有执行
         *
         */
        if (start < length - step + 1)
            Merge(resultArrays, sortingArrys, start, start + step - 1, length - 1);
        else
            for (int j = start; j < length; j++)
                resultArrays[j] = sortingArrys[j];

    }

}
