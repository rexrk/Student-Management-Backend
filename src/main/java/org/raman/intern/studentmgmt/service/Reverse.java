package org.raman.intern.studentmgmt.service;

public class Reverse {
    public static void main(String[] a) {
        String st = "abcdefgh";
        st = reverseString(st, 0);
        System.out.println(st);
    }

    public static String reverseString(String st, int i) {
        if(i == st.length()/2) return st;
        char[] arr = st.toCharArray();
        char temp = arr[i];
        arr[i] = arr[st.length() - i - 1];
        arr[st.length() - i - 1] = temp;
        st = new String(arr);
        return reverseString(st, i+1);
    }
}
