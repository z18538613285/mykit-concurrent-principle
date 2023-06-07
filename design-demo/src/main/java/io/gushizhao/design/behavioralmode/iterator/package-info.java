package io.gushizhao.design.behavioralmode.iterator;
/**
 * 迭代器模式
 * 一、概述
 * 给定一个语言，定义它的文法的一种表示，并定义一个解释器，这个解释器使用该表示来解释语言中的
 * 句子。
 *
 * 二、适用性
 * 访问一个聚合对象的内容而无需暴露它的内部表示。
 * 支持对聚合对象的多种遍历。
 * 为遍历不同的聚合结构提供一个统一的接口(即,支持多态迭代)。
 *
 * 三、参与者
 * Iterator 迭代器定义访问和遍历元素的接口。
 * ConcreteIterator 具体迭代器实现迭代器接口。 对该聚合遍历时跟踪当前位置。
 * Aggregate 聚合定义创建相应迭代器对象的接口。 4.ConcreteAggregate 具体聚合实现创建相应
 * 迭代器的接口，该操作返回ConcreteIterator的一个适当的实例.
 *
 *
 */