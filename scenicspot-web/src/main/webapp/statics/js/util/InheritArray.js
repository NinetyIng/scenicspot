/*算法封装    */

//冒泡排序	
Array.prototype.bubbleSort=function(){
	
	if(this && this.lengt > 1){
		for (var i = 0; i < this.length; i++) {
                for (var j = this.length; j > 0; j--) {
                    if (this[j] < this[j - 1]) {
                        var temp = this[j - 1];
                        this[j - 1] = this[j];
                        this[j] = temp;
                    }
         }
     }
	}
}
//插入排序
Array.prototype.insertSort = function(){
	if(this && this.length > 1){
	var temp;
    for (var i = 1; i < this.length; i++) {
        var temp = this[i];
        for (var j = i; j > 0 && temp < this[j - 1]; j--) {
            this[j] = this[j - 1];
        }
        this[j] = temp
    }
	}
}


//选择排序
Array.prototype.selectSort = function(){
	
	 if(this && this.length > 1){
	 	var min, temp; ;
     for (var i = 0; i < array.length; i++) {
        min = i;
        for (var j = i + 1; j < array.length; j++) {
            if (array[min] > array[j])
                min = j;
        }
        if (min != i) {
            temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
       }
	 	
	 }
}
//数组移除某个值
Array.prototype.removeValue  = function(ary){
	 var index = this.indexOf(val);
     if (index > -1) {
        this.splice(index, 1);
        return this;
     }
}
/**
 *遍历数组  参数是函数   自己的操作 
 * @param {Object} fn
 */
Array.prototype.each = function(fn){
		this.i|| (this.i = 0);
		if(this.length > 0 && fn.constructor == Function){
			while(this.i < this.length){
				var item = this[this.i];
				if(item && item.constructor == Array){
					item.each(fn);
				}else{
					fn.call(fn,item); 
				}
				this.i ++;
			}
	   }
	   return this;
}
		








