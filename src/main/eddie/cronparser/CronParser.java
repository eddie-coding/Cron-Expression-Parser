import java.util.ArrayList;
import java.util.List;

public class CronParser {
    
    public static String parseCronString(String expression){

        String cronString = expression;

        String[] fields = cronString.split("\\s+");
        
        if (fields.length != 6) {
            System.out.println("Invalid cron string: must contain 6 fields");
            System.exit(1);
        }

        List<String> fieldNames = List.of("minute", "hour", "day of month", "month", "day of week");
        List<List<String>> expandedFields = List.of(
            expandField(fields[0], 0, 59),
            expandField(fields[1], 0, 23),
            expandField(fields[2], 1, 31),
            expandField(fields[3], 1, 12),
            expandField(fields[4], 0, 6)
        );

        StringBuilder formattedOutput = new StringBuilder();
        for (int i = 0; i < fieldNames.size(); i++) {
            appendField(formattedOutput, fieldNames.get(i), expandedFields.get(i));
        }
        formattedOutput.append("command").append(" ".repeat(14 - "command".length())).append(fields[5]);

        return formattedOutput.toString();

    }

    private static void appendField(StringBuilder output, String fieldName, List<String> values) {
        output.append(fieldName).append(" ".repeat(14 - fieldName.length())).append(String.join(" ", values)).append("\n");
    }

    public static List<String> expandField(String field, int min, int max) {
        List<String> expanded = new ArrayList<>();
        if (field.equals("*") || field.equals("?")) {
            for (int i = min; i <= max; i++) {
                expanded.add(String.valueOf(i));
            }
        } else if (field.startsWith("*/")) {
            int step = Integer.parseInt(field.substring(2));
            if(step > max){
                System.out.println("Invalid cron string: Step size is more than maximum value");
                System.exit(1);
            }
            for (int i = min; i <= max; i += step) {
                expanded.add(String.valueOf(i));
            }
        } else {
            String[] parts = field.split(",");
            for (String part : parts) {
                if (part.contains("/")) {
                    String[] stepParts = part.split("/");
                    int start = Integer.parseInt(stepParts[0]);
                    int step = Integer.parseInt(stepParts[1]);
                    if(step > max){
                        System.out.println("Invalid cron string: Step size is more than maximum value");
                        System.exit(1);
                    }
                    for (int i = start; i <= max; i += step) {
                        expanded.add(String.valueOf(i));
                    }
                } else if (part.contains("-")) {
                    String[] rangeParts = part.split("-");
                    int start = Integer.parseInt(rangeParts[0]);
                    int end = Integer.parseInt(rangeParts[1]);
                    for (int i = start; i <= end; i++) {
                        expanded.add(String.valueOf(i));
                    }
                } else {
                    try {
                        int value = Integer.parseInt(part);
                        if (value < min || value > max) {
                            System.out.println("Invalid cron string: Value out of range");
                            System.exit(1);
                        }
                        expanded.add(part);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid cron string: Non-numeric value");
                        System.exit(1);
                    }
                }
            }
        }
        return expanded;
    }
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java CronParser '<cron_string>'");
            System.exit(1);
        }
    System.out.println(parseCronString(args[0].toString()));
        
    }
}

