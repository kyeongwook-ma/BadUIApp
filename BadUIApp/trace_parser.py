# -*- coding:utf-8 -*-
import sys, os, json, subprocess

def get_lines(file_path):
    return list(open(file_path).read().splitlines())

def parse(line, keyword):
    fragments = line.split("\t")

    try:
        if keyword in fragments[1]:
            return { "file_name" : fragments[4], "method_name" : fragments[2]}
    except IndexError:
        pass

def get_pid(keyword):
    p = subprocess.Popen("adb shell ps | grep " + keyword, stdout=subprocess.PIPE, shell=True)
    ps_result = p.stdout.readline()

    for ps in ps_result:
        if keyword in ps:
            pass

def main():
    if len(sys.argv) < 3:
        print('Usage : python trace_parser.py "tracefile path" "keyword" result.txt ')
        sys.exit(1)

    lines = get_lines(sys.argv[1])
    out = open(sys.argv[3], "w")

    for line in lines[24:]:
        info_dict = parse(line, sys.argv[2])

        if info_dict is not None:
            out.write(str(info_dict)+"\n")


if __name__ == "__main__":
    main()
