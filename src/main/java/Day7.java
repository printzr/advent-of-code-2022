import utils.FileUtils;

import java.util.*;

public class Day7 {
    private static String INPUT_FILENAME = "/input/day7_input.txt";

    public static void main(String[] args) throws Exception {
        File topDirectory = File.createDirectory("/", null);
        File currentDirectory = topDirectory;
        int lineCount = 0;

        List<String> lines = FileUtils.getInputLines(INPUT_FILENAME);
        for (String line : lines) {
            lineCount++;
            System.out.println("currentDirectory = " + currentDirectory);
            System.out.println("line(" + lineCount+"): "+line);
            if ("$ cd /".equals(line)) {
                currentDirectory = topDirectory;
                System.out.println("Set top level dir");
            } else if (line.startsWith("$")) {
                //command
                if ("$ ls".equals(line)) {
                    //skip
                    System.out.println("List files");
                } else if("$ cd ..".equals(line)) {
                    System.out.println("Return to parent");
                    currentDirectory = currentDirectory.parent;
                } else if (line.startsWith("$ cd")) {
                    System.out.println("set dir");
                    String[] parts = line.split(" ");
                    String folderName = parts[2];
                    System.out.println("folderName = " + folderName);
                    System.out.println("currentDirectory.files = " + currentDirectory.files);
                    currentDirectory = currentDirectory.files.stream()
                            .filter(File::isDirectory)
                            .filter(x -> folderName.equals(x.filename))
                            .findFirst().get();
                }
            } else {
                if (line.startsWith("dir")) {
                    //directory
                    String[] parts = line.split(" ");
                    String directoryName = parts[1];
                    if( currentDirectory.containsDirectory(directoryName)) {
                        //skip
                        System.out.println("Dir Already Exists");
                    } else {
                        //create dir
                        System.out.println("Create dir");
                        currentDirectory.files.add(File.createDirectory(directoryName,currentDirectory));
                    }

                } else {
                    //file
                    System.out.println("Create file");
                    String[] parts = line.split(" ");
                    String filename = parts[1];
                    int size = Integer.valueOf(parts[0]);
                    currentDirectory.files.add(File.createFile(filename,size,currentDirectory));
                }
            }
        }

        System.out.println("topDirectory.getSize = " + topDirectory.getSize());
        List<File> foldersWithSize = getFoldersWithSize(topDirectory.files);
        System.out.println("foldersWithSize.size() = " + foldersWithSize.size());
        int total = foldersWithSize.stream().map(File::getSize).reduce(0, Integer::sum);
        System.out.println("total = " + total);
        //1297683 correct

        //Disk: 70000000
        //Used: 44804833
        //Free: 25195167
        //Needed: 30000000
        int sizeNeeded = 30000000 - (70000000-topDirectory.getSize());
        System.out.println("sizeNeeded = " + sizeNeeded);
        
        List<File> allFolders = getAllFolders(topDirectory.files);
        System.out.println("allFolders.size() = " + allFolders.size());
        Collections.sort(allFolders, Comparator.comparing(File::getSize));
//        for(File temp : allFolders) {
//            System.out.println("temp.getSize() = " + temp.getSize());
//        }
        File dirToDelete = allFolders.stream().filter(x-> x.getSize() >= sizeNeeded).findFirst().get();
        System.out.println("dirToDelete = " + dirToDelete);
        System.out.println("dirToDelete.getSize = " + dirToDelete.getSize());
        //5756764 correct
    }

    public static List<File> getAllFolders(List<File> files) {
        List<File> allFolders = new ArrayList<>();
        for( File file : files ) {
            if(file.isDirectory) {
                allFolders.add(file);
                allFolders.addAll(getAllFolders(file.files));
            }
        }
        return allFolders;
    }

    public static List<File> getFoldersWithSize(List<File> files) {
        List<File> foldersWithSize = new ArrayList<>();
        for( File file : files) {
            if(file.isDirectory && file.getSize() <= 100000) {
                foldersWithSize.add(file);
            }
            foldersWithSize.addAll(getFoldersWithSize(file.files));
        }
        return foldersWithSize;
    }

    public static class File {
        public String filename;
        public boolean isDirectory;
        public int size;
        public List<File> files;
        public File parent;

        public File() {
            this.filename = null;
            this.isDirectory = true;
            this.size = 0;
            this.files = new ArrayList<>();
            this.parent = null;
        }

        public File(String filename, boolean isDirectory, int size, List<File> files, File parent) {
            this.filename = filename;
            this.isDirectory = isDirectory;
            this.size = size;
            this.files = files;
            this.parent = parent;
        }

        public static File createFile(String filename, int size, File parent) {
            return new File(filename, false, size, new ArrayList<>(), parent);
        }

        public static File createDirectory(String filename, File parent) {
            return new File(filename, true, 0, new ArrayList<>(), parent);
        }

        public int getSize() {
            if (isDirectory) {
                return this.files.stream().map(File::getSize).reduce(0, Integer::sum);
            } else {
                return this.size;
            }
        }

        public boolean isDirectory() {
            return isDirectory;
        }

        public boolean containsDirectory(String directoryName) {
            return this.files.stream()
                    .filter(File::isDirectory)
                    .filter(x -> directoryName.equals(x.filename))
                    .findFirst()
                    .isPresent();
        }

        @Override
        public String toString() {
            return "File{" +
                    "filename='" + filename + '\'' +
                    ", isDirectory=" + isDirectory +
                    ", size=" + size +
                    ", files count=" + (files == null ? "":files.size() )+
                    ", parent name=" + (parent == null ? "":parent.filename) +
                    '}';
        }
    }


}

