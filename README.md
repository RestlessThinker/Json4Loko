Json4Loko
=========

A really simple wrapper for JSON parsers/mappers/serializers so you can test them.

Some comparative results for 1000 objects:

# Java #

## Versions: gson 1.7.1 vs. jackson 1.9.9 ## 

### Array to JSON ###

* gson **169 ms**
 
  jackson **56 ms**

### List to JSON ###

* gson **98 ms**
 
  jackson **28 ms**

### JSON to List ###

* gson **102 ms**
 
  jackson **76 ms**

## Versions: gson 2.2.2 vs. jackson 2.0.6 ##

### Array to JSON ### 

* gson **62 ms**

  jackson **63 ms**

The mean of 1000 attempts of Array to Json Gson is: **2.057** with a *standard deviation* of: **1.3091031280995402**

The mean of 1000 attempts of Array to Json Jackson is: **0.563** with a *standard deviation* of: **1.0315187831542376**

### List to JSON ###

* gson **32 ms**

  jackson **26 ms**

The mean of 1000 attempts of List to Json Gson is: **1.695** with a *standard deviation* of: **0.5310131825105666**

The mean of 1000 attempts of List to Json Jackson is: **0.497** with a *standard deviation* of: **0.531028247836214**

### JSON to List ###

* gson **43 ms**

  jackson **60 ms**

The mean of 1000 attempts of Json to List Gson is: **1.177** with a *standard deviation* of: **0.8542078201468292**

The mean of 1000 attempts of Json to List Jackson is: **0.864** with a *standard deviation* of: **1.2733828960685771**


# Android #

## Versions: gson 2.2.2 vs jackson 2.0.6 ##

### Array to JSON ###

* gson **4970 ms **

  jackson **2187 ms **

The mean of 1000 attempts of Array to Json Gson is: **190.144** with a *standard deviation* of: **28.01533979804635**

The mean of 1000 attempts of Array to Json Jackson is: **85.332** with a *standard deviation* of: **7.046685461974312**


### List to JSON ###
 
* gson **243 ms**

  jackson **180 ms**

The mean of 1000 attempts of List to Json Gson is: **176.171** with a *standard deviation* of: **9.034254756204293**

The mean of 1000 attempts of List to Json Jackson is: **70.55** with a *standard deviation* of: **15.916642233838134**


### JSON to List ###

* gson **315 ms**

  jackson **293 ms**

The mean of 1000 attempts of Json to List Gson is: **127.407** with a *standard deviation* of: **28.32944318196177**

The mean of 1000 attempts of Json to List Jackson is: **142.809** with a *standard deviation* of: **25.109012704604687**


