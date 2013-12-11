//write kazua
 
if (!Array.prototype.reduceLeft) {
        Array.prototype.reduceLeft = function(callback, itl){
                var r = itl;
                for ( var i = 0; i < this.length; i++)
                        if(typeof r !== 'undefined') r = callback(r, this[i]);
                        else r = this[i];
                return r;
        };
}
if (!Array.prototype.reduceRight) {
        Array.prototype.reduceRight = function(callback, itl){
                var r = itl;
                for ( var i = this.length - 1; i >= 0; i--)
                        if(typeof r !== 'undefined') r = callback(this[i], r);
                        else r = this[i];
                return r;
        };
}
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
                if (this.length == 0)
                        return this;
                var na = [];
                for ( var i = 0; i < this.length; i++)
                        if (!na.contains(this[i]))
                                na.push(this[i]);
 
                return na;
        };
}
if (!Array.prototype.zip) {
        Array.prototype.zip = function(array) {
                var len = this.length > array.length ? array.length : this.length;
                if (len == 0)
                        return [];
                var na = [];
                for ( var i = 0; i < len; i++)
                        na.push([ this[i], array[i] ]);
 
                return na;
        };
}
if (!Array.prototype.zipWithIndex) {
        Array.prototype.zipWithIndex = function() {
                if (this.length == 0)
                        return this;
                var na = [];
                for ( var i = 0; i < this.length; i++)
                        na.push([ this[i], i ]);
 
                return na;
        };
}
if (!Array.prototype.takeWhile) {
        Array.prototype.takeWhile = function(fnc) {
                if (this.length == 0)
                        return this;
                var na = [];
                for ( var i = 0; i < this.length; i++) {
                        if (!fnc(this[i]))
                                break;
                        na.push(this[i]);
                }
                return na;
        };
}
if (!Array.prototype.dropWhile) {
        Array.prototype.dropWhile = function(fnc) {
                if (this.length == 0)
                        return this;
                var na = [];
                for ( var i = 0; i < this.length; i++)
                        if (!fnc(this[i]))
                                return this.slice(i);
        };
}
if (!Array.prototype.head) {
        Array.prototype.head = function() {
                return this.slice(0, 1);
        };
}
if (!Array.prototype.tail) {
        Array.prototype.tail = function() {
                return this.slice(1);
        };
}
if (!Array.prototype.last) {
        Array.prototype.last = function() {
                return this.slice(this.length - 1, this.length);
        };
}
if (!Array.prototype.init) {
        Array.prototype.init = function() {
                return this.slice(0, this.length - 1);
        };
}