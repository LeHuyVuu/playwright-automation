package com.huyvuu;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(1000, 750);
       //       loginDemo(page);
      //        dinoGame(page);
         AutoCapture(page, playwright, browser);
//        while (true){
//            playYouTubeVideoWithAutoSkip(page);
//        }


    }

    private static void playYouTubeVideoWithAutoSkip(Page page) {
        page.navigate("https://www.youtube.com/watch?v=zoEtcR5EW08");
        page.waitForLoadState();

        // Nhấn nút Play nếu cần thiết
        try {
            page.locator("button[aria-label='Play']").click();
        } catch (Exception e) {
            System.out.println("Nút Play không xuất hiện hoặc đã phát.");
        }

        // Kiểm tra nút "Skip Ads" liên tục trong khoảng thời gian ngắn
        for (int i = 0; i < 30; i++) {
            try {
                // Tìm và nhấn nút "Skip Ads" nếu có (dựa trên lớp CSS)
                if (page.locator(".ytp-skip-ad-button").isVisible()) {
                    page.locator(".ytp-skip-ad-button").click();
                    System.out.println("Đã bấm Skip Ads");
                    page.waitForTimeout(500); // Đợi 0.5 giây sau khi nhấn
                }
            } catch (Exception e) {
                // Bỏ qua nếu không thấy nút "Skip Ads"
            }

            try {
                // Tìm và nhấn nút "Skip" nếu có (dựa trên lớp CSS)
                if (page.locator(".ytp-skip-ad-button__text").isVisible()) {
                    page.locator(".ytp-skip-ad-button__text").click();
                    System.out.println("Đã bấm Skip");
                    page.waitForTimeout(500); // Đợi 0.5 giây sau khi nhấn
                }
            } catch (Exception e) {
                // Bỏ qua nếu không thấy nút "Skip"
            }

            // Tua video bằng phím mũi tên nếu không có quảng cáo
            page.keyboard().press("ArrowRight");

            // Đợi 0.5 giây trước khi kiểm tra lại, giúp phát hiện kịp thời nút Skip Ads
            page.waitForTimeout(500);

            // Kiểm tra nếu video đã kết thúc
            boolean isVideoEnded = (boolean) page.evaluate("document.querySelector('video').ended");
            if (isVideoEnded) {
                System.out.println("Video kết thúc, phát lại...");
                break; // Thoát khỏi vòng lặp và phát lại video từ đầu
            }
        }
    }


    private static void loginDemo(Page page) {
        // Điều hướng đến trang login
        page.navigate("https://demo.guru99.com/V4/"); // Thay thế bằng URL thực tế của trang login

        // Nhập User ID
        page.locator("//input[@name='uid']").fill("mngr598886"); // Thay thế với trường locator đúng của User ID

        // Nhập Password
        page.locator("//input[@name='password']").fill("UjejAsy"); // Thay thế với trường locator đúng của Password

        // Nhấn vào nút LOGIN
        page.locator("//input[@name='btnLogin']").click(); // Thay thế nếu locator khác

        // Có thể đợi phản hồi hoặc kiểm tra sau khi login
        page.waitForLoadState();
    }

    private static void AutoCapture(Page page, Playwright playwright, Browser browser) {
        page.navigate("https://en.wikipedia.org/wiki/Web_scraping");

        // Chụp ảnh màn hình nhiều lần và cuộn trang
        for (int i = 0; i < 10; i++) {
            // Cuộn xuống một khoảng
            page.evaluate("window.scrollBy(0, window.innerHeight);");

            // Chờ một chút để nội dung tải sau khi cuộn
            page.waitForTimeout(1000);

            // Chụp ảnh màn hình và lưu vào thư mục
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshot" + i + ".png")));

            // Thời gian giữa các lần chụp (500ms)
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        browser.close();
        playwright.close();
    }

    private static void dinoGame(Page page) {
        // Mở game khủng long trực tuyến
        page.navigate("https://trex-runner.com/");

        // Đợi game tải xong và bắt đầu
        page.waitForLoadState();
        page.keyboard().press("Space"); // Bắt đầu game

        // Vòng lặp chính để phát hiện và nhảy qua chướng ngại vật
        while (true) {
            // Kiểm tra vị trí chướng ngại vật bằng JavaScript
            Boolean shouldJump = (Boolean) page.evaluate("() => { " +
                    "const obstacle = Runner.instance_.horizon.obstacles[0];" +
                    "if (obstacle && obstacle.xPos < 110 && obstacle.xPos > 60) {" + // Xác định khoảng cách để nhảy
                    "    return true;" +
                    "} else {" +
                    "    return false;" +
                    "}" +
                    "}");
            if (shouldJump) {
                // Nhấn và giữ phím "Space" trong 100ms
                page.keyboard().down("Space"); // Giữ phím "Space"
                try {
                    Thread.sleep(85); // Thời gian giữ phím, có thể tăng nếu cần nhấn mạnh hơn
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                page.keyboard().up("Space"); // Thả phím "Space"
            }
        }
    }
}