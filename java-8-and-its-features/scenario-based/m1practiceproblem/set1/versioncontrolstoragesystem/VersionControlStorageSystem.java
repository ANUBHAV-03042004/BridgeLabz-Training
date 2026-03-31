package m1practiceproblem.set1.versioncontrolstoragesystem;

import java.util.*;
import java.util.stream.Collectors;

class Version {
    private String fileType;
    private String version;
    private int size;

    public Version(String fileType, String version, int size) {
        this.fileType = fileType;
        this.version = version;
        this.size = size;
    }

    public String getFileType() {
        return fileType;
    }

    public String getVersion() {
        return version;
    }

    public int getSize() {
        return size;
    }
}

public class VersionControlStorageSystem {
    static List<Version> versionStorage = new ArrayList<>();

    public static void Upload(Version v) {
        boolean exists = versionStorage.stream()
                .anyMatch(ver -> ver.getFileType().equalsIgnoreCase(v.getFileType())
                        && ver.getVersion().equalsIgnoreCase(v.getVersion()));
        if (!exists) {
            versionStorage.add(v);
           
        }
    }

    public static void fetch(String fileType) {
        List<Version> results = versionStorage.stream()
                .filter(v -> v.getFileType().equalsIgnoreCase(fileType))
                .sorted(Comparator.comparingInt(Version::getSize)
                        .thenComparing(Version::getVersion, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("File Not Found");
        } else {
            results.forEach(v -> System.out.println(v.getFileType() + " " + v.getVersion() + " " + v.getSize()));
        }
    }


    public static void latest(String fileType) {
        Optional<Version> latest = versionStorage.stream()
                .filter(v -> v.getFileType().equalsIgnoreCase(fileType))
                .reduce((first, second) -> second);

        latest.ifPresentOrElse(
                v -> System.out.println(v.getFileType() + " " + v.getVersion() + " " + v.getSize()),
                () -> System.out.println("File Not Found")
        );
    }

    public static void totalStorage(String fileType) {
        List<Version> results = versionStorage.stream()
                .filter(v -> v.getFileType().equalsIgnoreCase(fileType))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("File Not Found");
        } else {
            int sum = results.stream().mapToInt(Version::getSize).sum();
            System.out.println(fileType + " " + sum);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfTimes = sc.nextInt();
        sc.nextLine();

while (numOfTimes-- > 0) {

    String input = sc.nextLine().trim();
    if (input.length() == 0) continue;

    String[] inputArray = input.split("\\s+");
    String command = inputArray[0];
            if (command.equalsIgnoreCase("Upload")) {
                Version v = new Version(inputArray[1], inputArray[2], Integer.parseInt(inputArray[3]));
                Upload(v);
            } else if (command.equalsIgnoreCase("Fetch")) {
                fetch(inputArray[1]);
            } else if (command.equalsIgnoreCase("Latest")) {
                latest(inputArray[1]);
            } else if (command.equalsIgnoreCase("TOTAL_STORAGE")) {
                totalStorage(inputArray[1]);
            }
        }
    }
}