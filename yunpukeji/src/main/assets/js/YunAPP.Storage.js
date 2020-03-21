var YunAPP;
(function (YunAPP) {
    var Storage = (function () {
        function Storage() {
        }
        //会话存储
        Storage.Session = {
            //设置缓存项
            SetItem: function (key, value) {
                window.sessionStorage.setItem(key, value);
            },
            //获取缓存项
            GetItem: function (key) {
                return window.sessionStorage.getItem(key);
            },
            //删除缓存项
            DelItem: function (key) {
                if (window.sessionStorage!=null) {
                    window.sessionStorage.removeItem(key);
                }
            },
            //缓存长度
            Length: function () {
                return window.sessionStorage.length;
            },
            //获取缓存对象
            GetObject: function (key) {
                var str = window.sessionStorage.getItem(key);
                var obj = JSON.parse(str);
                return obj;
            },
            //设置缓存对象
            SetObject: function (key, obj) {
                var str = JSON.stringify(obj);
                window.sessionStorage.setItem(key, str);
            }
        };
        //本地存储
        Storage.Local = {
            //设置缓存项
            SetItem: function (key, value) {
                window.localStorage.setItem(key, value);
            },
            //获取缓存项
            GetItem: function (key) {
                return window.localStorage.getItem(key);
            },
            //删除缓存项
            DelItem: function (key) {
                if (window.localStorage != null) {
                    window.localStorage.removeItem(key);
                }
            },
            //缓存长度
            Length: function () {
                return window.localStorage.length;
            },
            //获取缓存对象
            GetObject: function (key) {
                var str = window.localStorage.getItem(key);
                var obj = JSON.parse(str);
                return obj;
            },
            //设置缓存对象
            SetObject: function (key, obj) {
                var str = JSON.stringify(obj);
                window.localStorage.setItem(key, str);
            }
        };
        return Storage;
    })();
    YunAPP.Storage = Storage;
})(YunAPP || (YunAPP = {}));
