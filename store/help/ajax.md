### ajax

#### 关于 contentType

- application/x-www-form-urlencoded (默认)
    - 传 name1=value1&name2=value2 (&连接)
    - 后端接收数据的对象不用加注解
- application/json
    - 传 {name1: "value1", name2: "value2"} json 字符串
    - 后端接受数据需加 @RequestBody 注解, 或通过 http 请求获取

#### from 表单序列化方法
JSON.stringify() 方法可将某个对象转换成 JSON 字符串形式
1. 将表单转换为字符串形式

    方法：$("#form").serialize()

    返回：name1=value1&name2=value2 (& 连接)


2. 转换为 json 对象形式
   
    方法：$("#form").serializeArray()
    
    返回：name1=value1&name2=value2 (这不就是第一种的字符串吗？？？)

    方法：JSON.stringify($("#form").serializeArray())

    返回：[{name: "name1", value: "value1"}, {name: "name2", value: "value2"}] (出来是个json对象，神奇！)


3. 转化为 json 字符串形式

    方法：$("#form-reg").serializeJSON()

    返回：name1=value1&name2=value2 (这不还是第一种的字符串吗？？？)

    方法：JSON.stringify($("#form-reg").serializeJSON())

    返回：{name1: "value1", name2: "value2"} (这才是 json 字符串)

~~~xml
    <!-- serializeJSON() 方法 -->
    <script>
        $.fn.serializeJSON=function() {
        	var serializeObj = {};
        	var array = this.serializeArray();
        	$(array).each(function () {
        		if (serializeObj[this.name]) {
        			if ($.isArray(serializeObj[this.name])) {
        				serializeObj[this.name].push(this.value);
        			} else {
        				serializeObj[this.name] = [serializeObj[this.name], this.value];
        			}
        		} else {
        			serializeObj[this.name] = this.value;
        		}
        	});
        	return serializeObj;
        };
    </script>
~~~

