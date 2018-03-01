which cygpath 2> /dev/null
					if [ $? = 1 ]; then
						BUILD_DIR="/proj/ucare/honglibu/hbase-0.94.12/target"
					else
						BUILD_DIR=`cygpath --unix '/proj/ucare/honglibu/hbase-0.94.12/target'`
					fi
                    if [ `ls $BUILD_DIR/nativelib | wc -l` -ne 0 ]; then
                      cp -PR $BUILD_DIR/nativelib/lib* $BUILD_DIR/hbase-0.94.12/hbase-0.94.12/lib/native/Linux-amd64-64
                    fi