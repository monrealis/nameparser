#!/bin/bash
letters=abcčdefghijklmnoprsštuvzž
for letter in $(echo $letters | grep -o .) ; do
	if [ "$letter" = "č" ] ; then letter="c-2"; fi
	if [ "$letter" = "ž" ] ; then letter="z-2"; fi
	if [ "$letter" = "š" ] ; then letter="s-2"; fi
	url=$(printf "http://vardai.vlkk.lt/sarasas/%s/" $letter)
	echo $url  
	curl --silent $url > $letter.html
	sleep 2
done