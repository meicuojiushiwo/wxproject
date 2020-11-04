package com.nbh.wxprojectclient.plugin.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class KafkaConstant {

    @Getter
    @AllArgsConstructor
    public enum CompressionType {

        ZSTD("zstd", "它可以将压缩速度交换为更高的压缩比率（压缩速度与压缩比率的权衡可以通过小增量来配置），反之亦然"),
        GZIP("gzip", "减少文件大小有两个明显的好处，一是可以减少存储空间，二是通过网络传输文件时，可以减少传输的时间。"),
        SNAPPY("snappy", "Snappy 是一个 C++ 的用来压缩和解压缩的开发包。其目标不是最大限度压缩或者兼容其他压缩格式，而是旨在提供高速压缩速度和合理的压缩率。Snappy 比 zlib 更快，但文件相对要大 20% 到 100%。在 64位模式的 Core i7 处理器上，可达每秒 250~500兆的压缩速度"),
        LZ4("lz4", "LZ4 是一种无损压缩算法，压缩速度为每核心 400 MB/s（0.16 字节/周期）。它拥有速度极快的解码器，速度为每核心多 GB/s（0.71 字节/周期）。此外，一种称为 LZ4_HC 的高压缩率衍生产品可用于交易可定制的 CPU 时间以实现高压缩率。"),
        PRODUCER("producer", "'producer'意味着压缩类型由'producer'决定");

        private String code;
        private String desc;

    }
}