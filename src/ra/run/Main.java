package ra.run;

import ra.entity.Laptop;
import ra.entity.LaptopType;

import java.util.*;
import java.util.regex.Pattern;

public class Main {
    private static List<LaptopType> laptopTypes = new ArrayList<>();
    private static List<Laptop> laptops = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("****************** LAPTOP-MANAGEMENT ******************");
            System.out.println("1. Quản lý loại laptop");
            System.out.println("2. Quản lý laptop");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    manageLaptopType(scanner);
                    break;
                case 2:
                    manageLaptop(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-3!");
            }
        }while(true);
    }

    private static void manageLaptopType(Scanner scanner) {
        boolean isExit = true;
        do{
            System.out.println("************* LAPTOP_TYPE-MENU ***");
            System.out.println("1. Hiển thị danh sách các loại laptop");
            System.out.println("2. Thêm mới loại laptop");
            System.out.println("3. Cập nhật thông tin loại laptop");
            System.out.println("4. Xóa loại Laptop");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayLaptopTypes();
                    break;
                case 2:
                    addLaptopType();
                    break;
                case 3:
                    updateLaptopType();
                    break;
                case 4:
                    deleteLaptopType();
                    break;
                case 5:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui long chọn từ 1-5! ");
            }

        }while(isExit);

    }


    private static void displayLaptopTypes() {
        System.out.println("Danh sách các loại laptop:");
        for (LaptopType type : laptopTypes) {
            if (!type.isDeleted()) {
                System.out.println(type.getTypeId() + " - " + type.getTypeName() + " - " + type.getDescription());
            }
        }
    }

    private static void addLaptopType() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên loại laptop: ");
        String name = scanner.nextLine();
        System.out.print("Nhập mô tả: ");
        String description = scanner.nextLine();

        if (name.isEmpty() || description.isEmpty()) {
            System.out.println("Tên loại laptop và mô tả không được để trống.");
            return;
        }

        for (LaptopType type : laptopTypes) {
            if (type.getTypeName().equalsIgnoreCase(name)) {
                System.out.println("Tên loại laptop đã tồn tại.");
                return;
            }
        }

        LaptopType newType = new LaptopType();
        newType.setTypeId(laptopTypes.size() + 1); // Auto-increment
        newType.setTypeName(name);
        newType.setDescription(description);
        newType.setDeleted(false);
        laptopTypes.add(newType);

        System.out.println("Thêm mới loại laptop thành công.");
    }

    private static void updateLaptopType() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã loại laptop cần cập nhật: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (LaptopType type : laptopTypes) {
            if (type.getTypeId() == id && !type.isDeleted()) {
                System.out.print("Nhập tên loại laptop mới (hoặc bấm Enter để giữ nguyên): ");
                String name = scanner.nextLine();
                System.out.print("Nhập mô tả mới (hoặc bấm Enter để giữ nguyên): ");
                String description = scanner.nextLine();

                if (!name.isEmpty()) {
                    type.setTypeName(name);
                }
                if (!description.isEmpty()) {
                    type.setDescription(description);
                }

                System.out.println("Cập nhật loại laptop thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy mã loại laptop.");
    }

    private static void deleteLaptopType() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã loại laptop cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (LaptopType type : laptopTypes) {
            if (type.getTypeId() == id && !type.isDeleted()) {
                type.setDeleted(true);
                System.out.println("Xóa loại laptop thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy mã loại laptop.");
    }


    private static void manageLaptop(Scanner scanner) {
        boolean isExit =true;
        do{
            System.out.println("************** LAPTOP-MENU *********************");
            System.out.println("1. Danh sách Laptop");
            System.out.println("2. Thêm mới Laptop");
            System.out.println("3. Cập nhật thông tin Laptop");
            System.out.println("4. Xóa Laptop");
            System.out.println("5. Thống kê số lượng laptop theo từng loại");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayLaptops();
                    break;
                case 2:
                    addLaptop();
                    break;
                case 3:
                    updateLaptop();
                    break;
                case 4:
                    deleteLaptop();
                    break;
                case 5:
                    statsLaptopByType();
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-6!");
            }
        }while (isExit);
    }

    private static void displayLaptops() {
        System.out.println("Danh sách Laptop:");
        for (Laptop laptop : laptops) {
            if (!laptop.isDeleted()) {
                System.out.println(laptop.getLaptopId() + " - " + laptop.getLaptopName() + " - " + laptop.getDescription() + " - " + laptop.getRam() + "GB - " + laptop.getWeight() + "kg - " + laptop.getLaptopPrice() + " USD - Loại: " + laptop.getTypeId());
            }
        }
    }

    private static void addLaptop() {
        Scanner scanner = new Scanner(System.in);
        String idRegex = "L[\\w]{4}";
        System.out.print("Nhập mã laptop (định dạng L****): ");
        String id = scanner.nextLine();
        System.out.print("Nhập tên laptop: ");
        String name = scanner.nextLine();
        System.out.print("Nhập mô tả: ");
        String description = scanner.nextLine();
        System.out.print("Nhập bộ nhớ RAM (GB): ");
        int ram = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập cân nặng máy (kg): ");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.print("Nhập giá tiền (USD): ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Nhập mã loại laptop:(dựa trên số tự tăng của loại laptop) ");
        int typeId = Integer.parseInt(scanner.nextLine());

        // Validate data
        if (id.isEmpty() || name.isEmpty() || description.isEmpty() || ram <= 0 || weight <= 0 || price <= 0 || typeId <= 0) {
            System.err.println("Dữ liệu nhập không hợp lệ.");
            return;
        }

        // Check for duplicate laptop ID
        for (Laptop laptop : laptops) {
            if (laptop.getLaptopId().equalsIgnoreCase(id)) {
                System.err.println("Mã laptop đã tồn tại.");
                return;
            }
        }

        // Check for existing typeId
        boolean typeExists = false;
        for (LaptopType type : laptopTypes) {
            if (type.getTypeId() == typeId && !type.isDeleted()) {
                typeExists = true;
                break;
            }
        }
        if (!typeExists) {
            System.err.println("Mã loại laptop không tồn tại.");
            return;
        }

        Laptop newLaptop = new Laptop();
        newLaptop.setLaptopId(id);
        newLaptop.setLaptopName(name);
        newLaptop.setDescription(description);
        newLaptop.setRam(ram);
        newLaptop.setWeight(weight);
        newLaptop.setLaptopPrice(price);
        newLaptop.setTypeId(typeId);
        newLaptop.setCreateAt(new Date());
        newLaptop.setDeleted(false);
        laptops.add(newLaptop);

        System.out.println("Thêm mới laptop thành công.");
    }

    private static void updateLaptop() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã laptop cần cập nhật: ");
        String id = scanner.nextLine();
        for (Laptop laptop : laptops) {
            if (laptop.getLaptopId().equalsIgnoreCase(id) && !laptop.isDeleted()) {
                System.out.print("Nhập tên laptop mới (hoặc bấm Enter để giữ nguyên): ");
                String name = scanner.nextLine();
                System.out.print("Nhập mô tả mới (hoặc bấm Enter để giữ nguyên): ");
                String description = scanner.nextLine();
                System.out.print("Nhập bộ nhớ RAM mới (hoặc bấm Enter để giữ nguyên): ");
                String ramInput = scanner.nextLine();
                System.out.print("Nhập cân nặng mới (hoặc bấm Enter để giữ nguyên): ");
                String weightInput = scanner.nextLine();
                System.out.print("Nhập giá tiền mới (hoặc bấm Enter để giữ nguyên): ");
                String priceInput = scanner.nextLine();
                System.out.print("Nhập mã loại laptop mới (hoặc bấm Enter để giữ nguyên): ");
                String typeIdInput = scanner.nextLine();

                if (!name.isEmpty()) {
                    laptop.setLaptopName(name);
                }
                if (!description.isEmpty()) {
                    laptop.setDescription(description);
                }
                if (!ramInput.isEmpty()) {
                    laptop.setRam(Integer.parseInt(ramInput));
                }
                if (!weightInput.isEmpty()) {
                    laptop.setWeight(Double.parseDouble(weightInput));
                }
                if (!priceInput.isEmpty()) {
                    laptop.setLaptopPrice(Double.parseDouble(priceInput));
                }
                if (!typeIdInput.isEmpty()) {
                    int typeId = Integer.parseInt(typeIdInput);
                    boolean typeExists = false;
                    for (LaptopType type : laptopTypes) {
                        if (type.getTypeId() == typeId && !type.isDeleted()) {
                            typeExists = true;
                            break;
                        }
                    }
                    if (typeExists) {
                        laptop.setTypeId(typeId);
                    } else {
                        System.err.println("Mã loại laptop không tồn tại.");
                        return;
                    }
                }

                System.err.println("Cập nhật laptop thành công.");
                return;
            }
        }
        System.err.println("Không tìm thấy mã laptop.");
    }

    private static void deleteLaptop() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã laptop cần xóa: ");
        String id = scanner.nextLine();
        for (Laptop laptop : laptops) {
            if (laptop.getLaptopId().equalsIgnoreCase(id) && !laptop.isDeleted()) {
                laptop.setDeleted(true);
                System.err.println("Xóa laptop thành công.");
                return;
            }
        }
        System.err.println("Không tìm thấy mã laptop.");
    }

    private static void statsLaptopByType() {
        Map<Integer, Integer> stats = new HashMap<>();
        for (Laptop laptop : laptops) {
            if (!laptop.isDeleted()) {
                stats.put(laptop.getTypeId(), stats.getOrDefault(laptop.getTypeId(), 0) + 1);
            }
        }

        System.out.println("Thống kê số lượng laptop theo từng loại:");
        for (Map.Entry<Integer, Integer> entry : stats.entrySet()) {
            for (LaptopType type : laptopTypes) {
                if (type.getTypeId() == entry.getKey() && !type.isDeleted()) {
                    System.out.println(type.getTypeName() + ": " + entry.getValue());
                }
            }
        }
    }
}