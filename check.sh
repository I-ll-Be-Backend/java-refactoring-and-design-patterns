#!/bin/sh
# Compiles every step of every lesson in eclipse-temurin:21-jdk: the check each lesson passed before its video went out.
# Needs Docker. A step folder holding .nocheck does not compile on purpose and is skipped.
docker run --rm --entrypoint /bin/sh -v "$PWD:/w" eclipse-temurin:21-jdk -c '
true
cd /w; fail=0
for f in */step-*/*.java; do
  [ -e "$f" ] || continue
  [ -e "$(dirname "$f")/.nocheck" ] && continue
  d=$(mktemp -d)
  if ( cp $f $d/Lesson.java && javac -d $d -proc:none -nowarn $d/Lesson.java ) >/tmp/log 2>&1; then echo "ok    $f"; else echo "FAIL  $f"; cat /tmp/log; fail=1; fi
done
exit $fail'
