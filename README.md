# ChannelPack
Android生成多渠道包工具  使用例子:

例如我这里使用了Bugly与友盟的统计，所以我在配置里面加入了两个字段 清单文件里面的value随意填写就好了，生成的时候会自动替换。

![](http://i.imgur.com/vLHYYkJ.png)
	
	生成渠道包之前，先打出一个正式包。
    创建一个json.config的文件 :
    
    '
    
                {
                    "apk": "C://apktool/app.apk",	//正式包的路径
                    "out": "C://apktool/",			//输出目录
                    "store_alias": "Pencilso",		//签名证书别名
                    "store_file": "C://apktool/signature.keystore",//签名文件路径
                    "store_pass": "Pencilso",		//签名证书密码
                    "meta-data": [
                        {
                            "UMENG_CHANNEL": "腾讯",
                            "BUGLY_APP_CHANNEL": "腾讯",
                            "key": "tx"		//这个key是用来生成apk文件名的时候用的
                        },
                        {
                            "UMENG_CHANNEL": "百度",
                            "BUGLY_APP_CHANNEL": "百度",
                            "key": "baidu"
                        },
                        {
                            "UMENG_CHANNEL": "360",
                            "BUGLY_APP_CHANNEL": "360",
                            "key": "s360"
                        }
                    ]
                }
我在工程里面提供了一个成品:ChannelPack.jar
新建一个文件夹，将ChannelPack.jar与json的配置文件放在一起。
然后执行终端指令:java -jar ChannelPack.jar

