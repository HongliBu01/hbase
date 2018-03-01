which cygpath 2> /dev/null
					if [ $? = 1 ]; then
						BUILD_DIR="/proj/ucare/honglibu/hbase-0.94.12/target"
					else
						BUILD_DIR=`cygpath --unix '/proj/ucare/honglibu/hbase-0.94.12/target'`
					fi

					cd $BUILD_DIR/hbase-0.94.12
					tar czf $BUILD_DIR/hbase-0.94.12.tar.gz hbase-0.94.12