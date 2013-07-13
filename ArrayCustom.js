//write kazua

if (!Array.prototype.contains) {
	Array.prototype.contains = function(value) {
		for ( var i = 0; i < this.length; i++)
			if (this[i] === value)
				return true;
		return false;
	};
}
if (!Array.prototype.distinct) {
	Array.prototype.distinct = function() {
		var t = Object(this), nt = [], len = t.length >>> 0;
		if (len == 0)
			return this;
		for ( var i = 0; i < t.length; i++)
			if (!nt.contain(t[i]))
				nt.push(t[i]);

		return nt;
	};
}

function test() {
	var a = new Array("1", "3", "4", "1", "5");
	a = a.distinct();
	alert(a);
}