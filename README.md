# ChannelPack
Android生成多渠道包工具
    配置文件:
    '
    
                {
                    "apk": "C://apktool/app.apk",
                    "out": "C://apktool/",
                    "store_alias": "Pencilso",
                    "store_file": "C://apktool/signature.keystore",
                    "store_pass": "Pencilso",
                    "meta-data": [
                        {
                            "UMENG_CHANNEL": "腾讯",
                            "BUGLY_APP_CHANNEL": "腾讯",
                            "key": "tx"
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