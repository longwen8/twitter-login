#第三方账号平台集成和Twitter账号登录
### 概述

如果项目中需要集成第三方账号登录，例如国内的QQ，微博，微信，国外的facebook,twitter等。以下说明可以帮忙解决一些问题。以下主要说明一下第三方集成的一些工具，并重点对twitter的集成进行说明。（相对其他类型账号坑比较多）

### 通用解决方法

主要参考网上一个开源项目 JustAuth. 源码和说明地址：https://github.com/justauth/JustAuth

是一个开源的开发包，已经完成了众多国内国外第三方平台的集成。可以直接在maven中配置如下配置，也可以将源码下载，直接拷贝实现类到项目中进行应用。具体使用方法可以参考github中的教程。

     <dependency>
        <groupId>me.zhyd.oauth</groupId>
        <artifactId>JustAuth</artifactId>
        <version>1.12.0</version>
    </dependency>

### Twitter登录
JuastAuth目前的版本不包括Twitter登录，本节对Twitter登录进行说明。
**开发者账号**

首先是开发者账号申请，相对其他比较困难。本人申请了两次都被拒绝了。后来在网上找了一个范例，可以申请通过。

参考网站：
https://www.extly.com/docs/autotweetng_joocial/tutorials/how-to-auto-post-from-joomla-to-twitter/apply-for-a-twitter-developer-account/#apply-for-a-developer-account

**登录流程**

首先需要了解一下Twitter的访问流程,步骤大致如下：
- 使用开发者账号创建一个应用，并配置相关信息，重点是回传URL。然后获取consumer_key和 consumer_secret,access_token和access_secret 四个值。


- 获取requestToken,requestToken 主要包含了 token和tokenSecret
- 通过reuqestToken信息，可以构造一个访问第三方账号的连接，然后用户可以进行登录和授权。
- 确认授权以后，twitter会自动调用回传路径，并将重要的参数回传。oauth_verifer.
- 使用requestToken和oauth_verifer参数，访问twitter服务器获取一个accessToken.
- 使用accessToken 可以调用任意的api获取用户信息的接口。（此接口可以多次重复调用）

twitter第三方登录主要参考官方说明：
https://developer.twitter.com/en/docs/twitter-for-websites/log-in-with-twitter/guides/implementing-sign-in-with-twitter


**实现方式**

- Twitter4j

  一种方式是使用Twitter提供的一个封装好的jar 包 Twitter4j,如下应用jar包，直接通过方法调用即可实现。
    
       <dependency>
            <groupId>org.twitter4j</groupId>
            <artifactId>twitter4j-core</artifactId>
            <version>4.0.7</version>
        </dependency>

- HTTP

  另一种方式就是根据规范，手动编码实现细节。主要包括如何构造消息头的Authorization 参数， 和参数顺序排列，以及参数加密，转码操作等等。


以上两种方式的实现可以参考如下例子，代码中对两种实现方式均有说明。

参考源码：https://github.com/longwen8/twitter-login




