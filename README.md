# Android MQTT Intrusion Alert App

## 📱 Giới thiệu dự án

Ứng dụng Android này được phát triển nhằm **nhận cảnh báo xâm nhập từ cảm biến chuyển động ESP32** thông qua **HiveMQ Cloud MQTT broker**. Ứng dụng sẽ:
- Hiển thị cảnh báo tức thời bằng thông báo đẩy.
- Lưu vĩnh viễn lịch sử các lần xâm nhập vào cơ sở dữ liệu nội bộ.
- Tiếp tục hoạt động và nhận tin nhắn dù ứng dụng không đang mở (chạy nền).
- Hỗ trợ xóa lịch sử cảnh báo.

Ứng dụng sử dụng:
-  HiveMQ Android MQTT Client (TLS 8883)
-  Room Database để lưu dữ liệu cục bộ
-  Notification Manager cho cảnh báo đẩy
- ⚙ Android Service để duy trì kết nối nền

---
## Phần cứng của dự án
Bạn có thể xem phần cứng của dự án và mô tả hoạt động chi tiết của dự án tại link: https://github.com/duongdangduy95/AuraAlert_esp32.git

##  Hướng dẫn sử dụng

### 1. **Yêu cầu**
- Thiết bị Android chạy Android 6.0 trở lên
- Kết nối Internet
- ESP32 đã được cấu hình để gửi MQTT đến HiveMQ Cloud

### 2. **Cài đặt**
- Clone hoặc tải về project này
- Mở bằng **Android Studio**
- Thay thế thông tin MQTT (host, username, password) trong `MqttManager.kt` bằng thông tin của bạn

### 3. **Chạy ứng dụng**
- Nhấn **Run** trong Android Studio hoặc build file `.apk` để cài đặt
- Ứng dụng sẽ tự động kết nối đến MQTT server khi mở
- Nhận cảnh báo xâm nhập qua notification
- Lịch sử được lưu ở màn hình chính (MainActivity)
   #### Lưu ý: bạn nên sử dụng logcat cho lần dùng đầu tiên sau khi nạp code vào điện thoại để theo dõi kết nối của app đến MQTT 
### 4. **Tùy chỉnh**
Bạn có thể:
- Thay đổi chủ đề MQTT (`TOPIC`) trong `MqttManager.kt`
- Thêm giao diện đẹp hơn bằng Jetpack Compose hoặc Material UI
- Tích hợp đăng nhập người dùng (Firebase, v.v.)
---

## Hình ảnh app hoạt động
- Trong Android Studio (khi nạp code cho máy Android và kiểm tra kết nối, bắt gói tin MQTT trong Logcat):

<img src="https://github.com/duongdangduy95/AuraAlert_esp32/raw/main/images/Screenshot%202025-05-20%20224357.png" width="500" />

- Hình ảnh thông báo hiện lên trên điện thoại:

<img src="https://github.com/duongdangduy95/AuraAlert_esp32/raw/main/images/hi%E1%BB%83n%20th%E1%BB%8B%20th%C3%B4ng%20b%C3%A1o%20tr%C3%AAn%20%C4%91i%E1%BB%87n%20tho%E1%BA%A1i.jpg" width="400"/>

- Hình ảnh giao diện app cảnh báo (khá đơn giản, nó chỉ lưu lịch sử phát hiện người và có nút xóa lịch sử):

<img src="https://github.com/duongdangduy95/AuraAlert_esp32/raw/main/images/h%C3%ACnh%20%E1%BA%A3nh%20app.jpg" width="400"/>

  

##  Lời cảm ơn

Cảm ơn các bạn đã dành thời gian xem và sử dụng dự án **AuraAlert_app**. Dự án này vẫn đang trong quá trình hoàn thiện, nên mọi ý kiến đóng góp của các bạn sẽ là nguồn động lực rất lớn giúp mình cải thiện sản phẩm tốt hơn.

##  Góp ý & phản hồi

Mình rất mong nhận được góp ý, nhận xét hoặc bất kỳ đề xuất nào từ các bạn.  
Nếu có thời gian, bạn có thể:

- Tạo một [issue](https://github.com/duongdangduy95/AuraAlert_app/issues) mới
- Gửi pull request
- Hoặc đơn giản là để lại một comment chia sẻ cảm nhận 😄

> ⚠️ **Lưu ý nhẹ nhàng**: Mong nhận được những góp ý **văn minh, tích cực** — nghiêm cấm "gạch đá" dưới mọi hình thức 😅

Cảm ơn các bạn rất nhiều! ❤️

