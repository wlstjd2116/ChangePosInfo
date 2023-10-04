package kr.kakao.map;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class KakaoMapMain {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("주소를 입력하세요 :");
            String address = reader.readLine();

            double[] coordinattes = KakaoAPI.getAddressCoordinate(address);

            if (coordinattes != null) {
                System.out.println("주소: " + address);
                System.out.println("위도: " + coordinattes[0]);
                System.out.println("경도: " + coordinattes[1]);
            } else {
                System.out.println("주소를 찾을 수 없습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

