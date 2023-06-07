package io.gushizhao.design.structuralmode.proxy;
/**
 * 代理模式
 * 一、概述
 * 为其他对象提供一种代理以控制对这个对象的访问。
 *
 * 二、适用性
 * 远程代理（RemoteProxy）为一个对象在不同的地址空间提供局部代表。
 * 虚代理（VirtualProxy）根据需要创建开销很大的对象。
 * 保护代理（ProtectionProxy）控制对原始对象的访问。 4.智能指引（SmartReference）取代了简
 * 单的指针，它在访问对象时执行一些附加操作。
 *
 * 三、参与者
 * Proxy 保存一个引用使得代理可以访问实体。若RealSubject和Subject的接口相同，Proxy会引用
 * Subject。 提供一个与Subject的接口相同的接口，这样代理就可以用来替代实体。 控制对实体的
 * 存取，并可能负责创建和删除它。 其他功能依赖于代理的类型：
 * RemoteProxy负责对请求及其参数进行编码，并向不同地址空间中的实体发送已编码的请求。
 * VirtualProxy可以缓存实体的附加信息，以便延迟对它的访问。
 * ProtectionProxy检查调用者是否具有实现一个请求所必需的访问权限。
 * Subject 定义RealSubject和Proxy的共用接口，这样就在任何使用RealSubject的地方都可以使用
 * Proxy。
 * RealSubject 定义Proxy所代表的实体
 *
 *
 *
 *
 */