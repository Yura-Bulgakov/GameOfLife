BINARY = app
LIBS = libs
OUT = out
LMBK = lombok-1.18.30.jar
YAML = snakeyaml-1.21.jar
SL4A = slf4j-api-1.7.32.jar
SL4I = slf4j-simple-1.7.32.jar
GUV1 = guava-31.1-jre.jar
GUV2 = failureaccess-1.0.1.jar
GUV3 = listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar
GUV4 = jsr305-3.0.2.jar
GUV5 = checker-qual-3.12.0.jar
GUV6 = error_prone_annotations-2.11.0.jar
GUV7 = j2objc-annotations-1.3.jar
DEPS = $(LIBS)/$(LMBK):$(LIBS)/$(YAML):$(LIBS)/$(SL4A):$(LIBS)/$(SL4I):$(LIBS)/$(GUV1):$(LIBS)/$(GUV2):$(LIBS)/$(GUV3):$(LIBS)/$(GUV4):$(LIBS)/$(GUV5):$(LIBS)/$(GUV6):$(LIBS)/$(GUV7)
WDEPS = $(LIBS)/$(LMBK);$(LIBS)/$(YAML);$(LIBS)/$(SL4A);$(LIBS)/$(SL4I);$(LIBS)/$(GUV1);$(LIBS)/$(GUV2);$(LIBS)/$(GUV3);$(LIBS)/$(GUV4);$(LIBS)/$(GUV5);$(LIBS)/$(GUV6);$(LIBS)/$(GUV7)

.PHONY: wlibs
wlibs:
	if exist $(LIBS) rmdir /q/s $(LIBS)
	mkdir $(LIBS)
	curl.exe https://repo1.maven.org/maven2/org/projectlombok/lombok/1.18.30/$(LMBK) --output $(LIBS)/$(LMBK)
	curl.exe https://repo1.maven.org/maven2/org/yaml/snakeyaml/1.21/$(YAML) --output $(LIBS)/$(YAML)
	curl.exe https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.32/$(SL4A) --output $(LIBS)/$(SL4A)
	curl.exe https://repo1.maven.org/maven2/org/slf4j/slf4j-simple/1.7.32/$(SL4I) --output $(LIBS)/$(SL4I)
	curl.exe https://repo1.maven.org/maven2/com/google/guava/guava/31.1-jre/$(GUV1) --output $(LIBS)/$(GUV1)
	curl.exe https://repo1.maven.org/maven2/com/google/guava/failureaccess/1.0.1/$(GUV2) --output $(LIBS)/$(GUV2)
	curl.exe https://repo1.maven.org/maven2/com/google/guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/$(GUV3) --output $(LIBS)/$(GUV3)
	curl.exe https://repo1.maven.org/maven2/com/google/code/findbugs/jsr305/3.0.2/$(GUV4) --output $(LIBS)/$(GUV4)
	curl.exe https://repo1.maven.org/maven2/org/checkerframework/checker-qual/3.12.0/$(GUV5) --output $(LIBS)/$(GUV5)
	curl.exe https://repo1.maven.org/maven2/com/google/errorprone/error_prone_annotations/2.11.0/$(GUV6) --output $(LIBS)/$(GUV6)
	curl.exe https://repo1.maven.org/maven2/com/google/j2objc/j2objc-annotations/1.3/$(GUV7) --output $(LIBS)/$(GUV7)

.PHONY: wcompile
wcompile: wlibs
	if exist $(OUT) rmdir /q/s $(OUT)
	mkdir $(OUT)
	cd src/main && dir /s /B  *.java > ..\..\temp.txt
	javac -cp $(WDEPS) -d $(OUT) @temp.txt -encoding UTF8
	del temp.txt


.PHONY: wrun
wrun: wcompile
	java -cp $(OUT);$(WDEPS) ru.project.Main

.PHONY: wpackage
wpackage: wcompile
	copy $(LIBS)\$(YAML) $(OUT)\ /b
	cd $(OUT) && jar xf $(YAML)
	del $(OUT)\$(YAML)

	copy $(LIBS)\$(SL4A) $(OUT)\ /b
	cd $(OUT) && jar xf $(SL4A)
	del $(OUT)\$(SL4A)

	copy $(LIBS)\$(SL4I) $(OUT)\ /b
	cd $(OUT) && jar xf $(SL4I)
	del $(OUT)\$(SL4I)

	copy $(LIBS)\$(GUV1) $(OUT)\ /b
	cd $(OUT) && jar xf $(GUV1)
	del $(OUT)\$(GUV1)

	copy $(LIBS)\$(GUV2) $(OUT)\ /b
	cd $(OUT) && jar xf $(GUV2)
	del $(OUT)\$(GUV2)

	copy $(LIBS)\$(GUV3) $(OUT)\ /b
	cd $(OUT) && jar xf $(GUV3)
	del $(OUT)\$(GUV3)

	copy $(LIBS)\$(GUV4) $(OUT)\ /b
	cd $(OUT) && jar xf $(GUV4)
	del $(OUT)\$(GUV4)

	copy $(LIBS)\$(GUV5) $(OUT)\ /b
	cd $(OUT) && jar xf $(GUV5)
	del $(OUT)\$(GUV5)

	copy $(LIBS)\$(GUV6) $(OUT)\ /b
	cd $(OUT) && jar xf $(GUV6)
	del $(OUT)\$(GUV6)

	copy $(LIBS)\$(GUV7) $(OUT)\ /b
	cd $(OUT) && jar xf $(GUV7)
	del $(OUT)\$(GUV7)

	jar cfm $(BINARY).jar src\main\resources\META-INF\MANIFEST.FM -C $(OUT) .

.PHONY: wrunjar
wrunjar: wpackage
	java -jar $(BINARY).jar

.PHONY: wclear
wclear:
	if exist $(OUT) rmdir /q/s $(OUT)
	if exist $(LIBS) rmdir /q/s $(LIBS)
	del $(BINARY).jar

.PHONY: libs
libs:
	rm -rf $(LIBS)
	mkdir $(LIBS)
	curl https://repo1.maven.org/maven2/org/projectlombok/lombok/1.18.30/$(LMBK) --output $(LIBS)/$(LMBK)
	curl https://repo1.maven.org/maven2/org/yaml/snakeyaml/1.21/$(YAML) --output $(LIBS)/$(YAML)
	curl https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.32/$(SL4A) --output $(LIBS)/$(SL4A)
	curl https://repo1.maven.org/maven2/org/slf4j/slf4j-simple/1.7.32/$(SL4I) --output $(LIBS)/$(SL4I)
	curl https://repo1.maven.org/maven2/com/google/guava/guava/31.1-jre/$(GUV1) --output $(LIBS)/$(GUV1)
	curl https://repo1.maven.org/maven2/com/google/guava/failureaccess/1.0.1/$(GUV2) --output $(LIBS)/$(GUV2)
	curl https://repo1.maven.org/maven2/com/google/guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/$(GUV3) --output $(LIBS)/$(GUV3)
	curl https://repo1.maven.org/maven2/com/google/code/findbugs/jsr305/3.0.2/$(GUV4) --output $(LIBS)/$(GUV4)
	curl https://repo1.maven.org/maven2/org/checkerframework/checker-qual/3.12.0/$(GUV5) --output $(LIBS)/$(GUV5)
	curl https://repo1.maven.org/maven2/com/google/errorprone/error_prone_annotations/2.11.0/$(GUV6) --output $(LIBS)/$(GUV6)
	curl https://repo1.maven.org/maven2/com/google/j2objc/j2objc-annotations/1.3/$(GUV7) --output $(LIBS)/$(GUV7)

.PHONY: compile
compile: libs
	rm -rf $(OUT)
	mkdir $(OUT)
	find ./src/main -name "*.java" > temp
	javac -classpath $(DEPS) -d $(OUT) @temp -encoding UTF8
	rm temp

.PHONY: run
run: compile
	java -cp $(OUT):$(DEPS) ru.project.Main

.PHONY: package
package: compile
	cp $(LIBS)/$(YAML) $(OUT)/
	cd $(OUT); jar xf $(YAML)
	rm $(OUT)/$(YAML)

	cp $(LIBS)/$(SL4A) $(OUT)/
	cd $(OUT); jar xf $(SL4A)
	rm $(OUT)/$(SL4A)

	cp $(LIBS)/$(SL4I) $(OUT)/
	cd $(OUT); jar xf $(SL4I)
	rm $(OUT)/$(SL4I)

	cp $(LIBS)/$(GUV1) $(OUT)/
	cd $(OUT); jar xf $(GUV1)
	rm $(OUT)/$(GUV1)

	cp $(LIBS)/$(GUV2) $(OUT)/
	cd $(OUT); jar xf $(GUV2)
	rm $(OUT)/$(GUV2)

	cp $(LIBS)/$(GUV3) $(OUT)/
	cd $(OUT); jar xf $(GUV3)
	rm $(OUT)/$(GUV3)

	cp $(LIBS)/$(GUV4) $(OUT)/
	cd $(OUT); jar xf $(GUV4)
	rm $(OUT)/$(GUV4)

	cp $(LIBS)/$(GUV5) $(OUT)/
	cd $(OUT); jar xf $(GUV5)
	rm $(OUT)/$(GUV5)

	cp $(LIBS)/$(GUV6) $(OUT)/
	cd $(OUT); jar xf $(GUV6)
	rm $(OUT)/$(GUV6)

	cp $(LIBS)/$(GUV7) $(OUT)/
	cd $(OUT); jar xf $(GUV7)
	rm $(OUT)/$(GUV7)

	jar cfm $(BINARY).jar ./src/main/resources/META-INF/MANIFEST.FM -C ./$(OUT)/ .

.PHONY: runjar
runjar: package
	java -jar $(BINARY).jar

.PHONY: clear
clear:
	rm -rf $(OUT)
	rm -rf $(LIBS)
	rm $(BINARY).jar
