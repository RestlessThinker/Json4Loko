Json4Loko
=========

A really simple wrapper for JSON parsers/mappers/serializers so you can test them.

Some comparative results for 1000 objects:

## Java ##

Versions: **gson 1.7.1** *vs.* **jackson 1.9.9**

* gson - Array to JSON time: **169 ms**
  
  jackson - Array to JSON time: **56 ms**

* gson - List to JSON time: **98 ms**
  
  jackson - List to JSON time: **28 ms**


* gson - JSON to List time: **102 ms**
  
  jackson - JSON to List time: **76 ms**

Versions: **gson 2.2.2** *vs.* **jackson 2.0.6**

* gson - Array to JSON time: **62 ms**

  jackson - Array to JSON time: **63 ms**

* gson - List to JSON time: **32 ms**

  jackson - List to json time: **26 ms**


* gson - JSON to List time: **43 ms**

  jackson - JSON to List time: **60 ms**

## Android ##

### Todo ###

* Create android test times
* Create standard deviation of the times.
