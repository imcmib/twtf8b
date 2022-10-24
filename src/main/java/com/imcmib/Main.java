package com.imcmib;

import org.apache.commons.cli.*;

public class Main {

    public static final Encoder ENCODER = new Encoder();

    public static void main(String[] args) {
        Options options = getOptions();
        CommandLineParser parser = new DefaultParser();
        HelpFormatter helper = new HelpFormatter();

        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("e")) {
                encode(cmd.getOptionValue("e"));
            } else if (cmd.hasOption("d")) {
                decode(Integer.parseInt(cmd.getOptionValue("d")));
            } else if (cmd.hasOption("h")) {
                printHelp(options, helper);
            } else {
                printHelp(options, helper);
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            printHelp(options, helper);
        }
    }

    private static Options getOptions() {
        Options options = new Options();

        Option encode = Option.builder("e")
                .longOpt("encode")
                .argName("string")
                .hasArg()
                .desc("Encode string")
                .build();
        options.addOption(encode);

        Option decode = Option.builder("d")
                .longOpt("decode")
                .argName("int")
                .hasArg()
                .desc("Decode integer")
                .build();
        options.addOption(decode);

        Option help = Option.builder("h")
                .longOpt("help")
                .desc("Print help")
                .build();
        options.addOption(help);

        return options;
    }

    private static void encode(String string) {
        int encoded = ENCODER.encode(string);
        System.out.println(encoded);
    }

    private static void decode(int value) {
        String decoded = ENCODER.decode(value);
        System.out.println(decoded);
    }

    private static void printHelp(Options options, HelpFormatter helper) {
        helper.printHelp("available arguments:", options);
    }
}
