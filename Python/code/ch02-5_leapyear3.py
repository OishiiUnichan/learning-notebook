year = int(input('西暦何年？'))

#うるう年判定
is_leap = (year%400==0)or((year%100!=0)and(year%4==0))

#結果判定
if is_leap:
    print('うるう年です')
else:
    print('平年です')

