//write kazua

if (!Array.prototype.randompop) {
	Array.prototype.randompop = function() {
		"use strict";
		if (this == null)
			throw new TypeError();
		var t = Object(this), len = t.length >>> 0;
		if (len == 0)
			return null;
		var r = Math.floor(Math.random() * len);
		var n = t[r];
		t = t.filter(function(value, index, array) {
			if (index != r)
				return true;
		});
		this.length = 0;
		Array.prototype.push.apply(this, t);
		return n;
	};
}
var a = new Array(1, 3, 4, 6, 7, 8, 0, 5, 9, 0);

function test() {
	var len = a.length;
	for (i = 0; i < len; i++) {
		var n = a.randompop();
		alert("popした要素" + n);
		alert("現配列" + a);
	}
}