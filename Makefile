# Java programs
JAVAC = javac
JAR = jar
JAVA = java

# Java compiler flags
JAVAFLAGS = -g

# Source directory
SOURCE = src

# Build directory
BUILD = build

# Output jar
OUT = Restaurant.jar

# Main class
MAINCLASS = Restaurant
MAINPACKAGE = CSE6431
MAINFILE = CSE6431/Restaurant.java

# The first target is the one that is executed when you invoke
# "make". 

all : archive
	$(MAKE) clean-build

build :
	mkdir -p $(BUILD)
	$(JAVAC) $(JAVAFLAGS) -sourcepath $(SOURCE) -cp $(BUILD) -d $(BUILD) $(SOURCE)/$(MAINFILE)

archive : build
	$(JAR) cvfe $(OUT) $(MAINPACKAGE).$(MAINCLASS) -C $(BUILD) .

clean-build :
	rm -rf $(BUILD)