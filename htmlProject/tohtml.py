import os
import sys
import nltk
nltk.download('punkt')

import nltk.data

HEAD = """
<!DOCTYPE html>
<html>
<head>
    <title>A láthatatlan ember</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
""".strip()

BODY = """
<body>
    <h1 align=center>{title}</h1>
    <table style="width:85%" align=center>
    {table}
    </table>
""".strip()

PREV = """
\t\t<a href="{prev}"><< previous </a>\n
""".strip()

NEXT = """
\t\t<a href="{next}"> next >></a>\n
""".strip()

FILL = """
<a href=\"https://mymemory.translated.net/en/Hungarian/English/{link}\" class="trans">{text}</a>
""".strip()

ROW = "\t<tr>\n\t\t\t<td valign=top align=left>{source}</td>\n\t\t\t<td>{text}</td>\n\t</tr>\n"


def createLine(line):
    """
    For each sentence in given line create a reference to it's translation.
    """
    lb = []
    tokenizer = nltk.data.load('tokenizers/punkt/english.pickle')
    fp = line
    sents = tokenizer.tokenize(fp)
    for sent in sents:
        lb.append(FILL.format(link='-'.join(sent.split()), text=sent))
    return ' '.join(lb)

def main():

    # load book titles in a list
    books = sorted( [ './books/' + f for f in os.listdir('./books/') if 'book' in f ] )

    # for each book create new .html page
    for i, book in enumerate(books, start=1):

        title = 'Book ' + str(i) + '.'
        ptable = ""

        with open(book, 'r') as b:
            for line in b:
                used = line.split('fu2019:')[-1].split(',')
                text = (','.join(used[1:])).rstrip(')\n')
                source = used[0]
                text = createLine(text)
                ptable += ROW.format(source=source, text=text)


        full = HEAD
        full += BODY.format(title=title, table=ptable)
        full += "\t<p align=center>\n"
        
        if not (i-2 < 0):
            path = '../html/' + books[i-2].split('/')[-1]
            full += PREV.format(prev=path)
        if i < len(books):
            path = '../html/' + books[i].split('/')[-1]
            full += NEXT.format(next=path)

        full += "\n\t\t\n</p></body>"

        fpath = './html/' + book.split('/')[-1]

        with open(fpath, 'w') as f:
            f.write(full)
        


if __name__ == '__main__':
    main()
