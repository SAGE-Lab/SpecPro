#! /usr/bin/perl
$formula = $ARGV[0];
# $mem_limit = $ARGV[1];
$mem_limit = 10000000;

@answer = `bash -c \"ulimit -c 0  -m $mem_limit -v $mem_limit; ltl2tgba -B \\"$formula\\" -d\"`;

print "@answer";
