all: class run

MainClass:=Main

class:
	cd ./src/game/ && javac -g -d ../../bin/ -encoding Shift_JIS *.java

run: # 画像が表示されない
	cd ./bin/ && java game.Main
