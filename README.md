#�������˺�ƽ̨���ɺ�Twitter�˺ŵ�¼
### ����

�����Ŀ����Ҫ���ɵ������˺ŵ�¼��������ڵ�QQ��΢����΢�ţ������facebook,twitter�ȡ�����˵�����԰�æ���һЩ���⡣������Ҫ˵��һ�µ��������ɵ�һЩ���ߣ����ص��twitter�ļ��ɽ���˵������������������˺ſӱȽ϶ࣩ

### ͨ�ý������

��Ҫ�ο�����һ����Դ��Ŀ JustAuth. Դ���˵����ַ��https://github.com/justauth/JustAuth

��һ����Դ�Ŀ��������Ѿ�������ڶ���ڹ��������ƽ̨�ļ��ɡ�����ֱ����maven�������������ã�Ҳ���Խ�Դ�����أ�ֱ�ӿ���ʵ���ൽ��Ŀ�н���Ӧ�á�����ʹ�÷������Բο�github�еĽ̡̳�

     <dependency>
        <groupId>me.zhyd.oauth</groupId>
        <artifactId>JustAuth</artifactId>
        <version>1.12.0</version>
    </dependency>

### Twitter��¼
JuastAuthĿǰ�İ汾������Twitter��¼�����ڶ�Twitter��¼����˵����
**�������˺�**

�����ǿ������˺����룬��������Ƚ����ѡ��������������ζ����ܾ��ˡ���������������һ����������������ͨ����

�ο���վ��
https://www.extly.com/docs/autotweetng_joocial/tutorials/how-to-auto-post-from-joomla-to-twitter/apply-for-a-twitter-developer-account/#apply-for-a-developer-account

**��¼����**

������Ҫ�˽�һ��Twitter�ķ�������,����������£�
- ʹ�ÿ������˺Ŵ���һ��Ӧ�ã������������Ϣ���ص��ǻش�URL��Ȼ���ȡconsumer_key�� consumer_secret,access_token��access_secret �ĸ�ֵ��


- ��ȡrequestToken,requestToken ��Ҫ������ token��tokenSecret
- ͨ��reuqestToken��Ϣ�����Թ���һ�����ʵ������˺ŵ����ӣ�Ȼ���û����Խ��е�¼����Ȩ��
- ȷ����Ȩ�Ժ�twitter���Զ����ûش�·����������Ҫ�Ĳ����ش���oauth_verifer.
- ʹ��requestToken��oauth_verifer����������twitter��������ȡһ��accessToken.
- ʹ��accessToken ���Ե��������api��ȡ�û���Ϣ�Ľӿڡ����˽ӿڿ��Զ���ظ����ã�

twitter��������¼��Ҫ�ο��ٷ�˵����
https://developer.twitter.com/en/docs/twitter-for-websites/log-in-with-twitter/guides/implementing-sign-in-with-twitter


**ʵ�ַ�ʽ**

- Twitter4j

  һ�ַ�ʽ��ʹ��Twitter�ṩ��һ����װ�õ�jar �� Twitter4j,����Ӧ��jar����ֱ��ͨ���������ü���ʵ�֡�
    
       <dependency>
            <groupId>org.twitter4j</groupId>
            <artifactId>twitter4j-core</artifactId>
            <version>4.0.7</version>
        </dependency>

- HTTP

  ��һ�ַ�ʽ���Ǹ��ݹ淶���ֶ�����ʵ��ϸ�ڡ���Ҫ������ι�����Ϣͷ��Authorization ������ �Ͳ���˳�����У��Լ��������ܣ�ת������ȵȡ�


�������ַ�ʽ��ʵ�ֿ��Բο��������ӣ������ж�����ʵ�ַ�ʽ����˵����

�ο�Դ�룺https://github.com/longwen8/twitter-login




