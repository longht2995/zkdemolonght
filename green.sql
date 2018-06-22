-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 22, 2018 lúc 11:09 AM
-- Phiên bản máy phục vụ: 10.1.31-MariaDB
-- Phiên bản PHP: 5.6.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `green`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Jacket'),
(2, 'T-Shirt');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `dateupdate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `date`, `description`, `name`, `price`, `category_id`, `dateupdate`) VALUES
(1, '2018-06-20 13:29:11', 'dsadasdadas', 'dsadasdsa', 2, 1, NULL),
(2, '2018-06-20 13:33:05', 'dddddddd', 'zzzzzzzzz', 3, 1, NULL),
(3, '2018-06-20 13:33:40', 'xxxxxxxxxxxxx', 'xxxxxxxxxxxx', 333333, 1, NULL),
(4, '2018-06-20 13:35:57', 'vvvvvvvv', 'vvvvvvvvvvv', 3333, 1, NULL),
(5, '2018-06-20 13:45:20', 'fffffffffff', 'fffffffff', 0, NULL, NULL),
(6, '2018-06-20 13:52:01', 'dddddddddd', 'ddddddddddd', 2, NULL, NULL),
(7, '2018-06-20 13:53:00', 'zzzzzzzz', 'zzzzzzzzzzzz', 2, NULL, NULL),
(8, '2018-06-20 13:55:58', 'vvvvvvvvvv', 'vvvvvvvv', 33333, 1, NULL),
(9, '2018-06-20 14:08:16', '7654321', '1234567', 1, 2, NULL),
(10, '2018-06-20 14:08:33', '3333333', 'dddddddd?', 3, 1, NULL),
(11, '2018-06-20 14:09:36', 'dddddddddddd', 'dddddddddd', 222, 1, NULL),
(12, '2018-06-20 14:10:14', '4444444444444', '4444444444', 222, 1, NULL),
(13, '2018-06-20 14:14:58', 'ddddddd', 'ddddddd', 1, 1, NULL),
(14, '2018-06-20 14:38:37', '2222222222', '22222222222', 22, 1, NULL),
(15, '2018-06-20 14:52:40', '0000000000', '000000000', 4444, 2, '2018-06-21 07:50:48'),
(16, '2018-06-20 14:53:11', '4444444', '4444444', 222, 1, '2018-06-20 15:07:34'),
(17, '2018-06-20 14:57:47', '22222222222', '2222222222', 222, 1, '2018-06-21 07:45:38'),
(18, '2018-06-21 07:47:56', '8888888888', '888888888', 888889000, 1, '2018-06-21 13:26:29'),
(29, '2018-06-22 15:07:39', 'ddddddddd', 'ddddddddd', 22222, 1, NULL),
(30, '2018-06-22 15:07:47', '33333333', '33333333', 3, 1, NULL);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_rlaghtegr0yx2c1q1s6nkqjlh` (`category_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK_rlaghtegr0yx2c1q1s6nkqjlh` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
