printf "Benchmark, Mini, C, OMini, OC\n" > stats.csv
cd benchmarks
export CLASSPATH=../../:$CLASSPATH
dirlist=$(ls)
for dir in $dirlist
do 
pushd $dir 
echo "=================== Running test" $dir "==================="
curTest=$(ls *.mini)
java Mini -dumpIL $curTest
rc=$?
if [[ $rc != 0 ]]; then echo "Failed to compile" $curTest; fi
curEx=$(ls *.s)
cEx=$(ls *.c)
gcc $curEx
rc=$?
if [[ $rc != 0 ]]; then echo "gcc failed to compile" $curEx; fi
# store mini Time into $mini
mini=$(echo $( TIMEFORMAT="%3U + %3S"; { time ./a.out < input > testOut; } 2>&1) "*1000" | bc -l)
gcc $cEx
# store c Time into $c
c=$(echo $( TIMEFORMAT="%3U + %3S"; { time ./a.out < input > testOut; } 2>&1) "*1000" | bc -l)
gcc -O3 $cEx
# store optimized c into $oC
oC=$(echo $( TIMEFORMAT="%3U + %3S"; { time ./a.out < input > testOut; } 2>&1) "*1000" | bc -l)
java Mini -dumpIL -Ocopy -OremoveUseless $curTest 
rc=$?
if [[ $rc != 0 ]]; then echo "Failed to compile" $curTest; fi
curEx=$(ls *.s)
gcc $curEx
rc=$?
if [[ $rc != 0 ]]; then echo "gcc failed to compile" $curEx; fi
oMini=$(echo $( TIMEFORMAT="%3U + %3S"; { time ./a.out < input > testOut; } 2>&1) "*1000" | bc -l)
echo $dir", "$mini", "$c", "$oMini", "$oC >> ../../stats.csv
diff testOut output > diffOut
rc=$?
if [[ $rc != 0 ]]; then echo "diff failed" $curEx; fi
popd
done
