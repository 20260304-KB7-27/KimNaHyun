
/*
* insertOne
* insertMany
* */

for (let i = 0 ; i < 2000; i++){
    db.numbers.insertOne({
        num : i,
    })
}


const docs =[];

for (let i = 0 ; i < 2000; i++){
    docs.push({
        num : i,
    })
}

db.numbers_2.insertMany(docs);

// 컬렉션의 문서 갯수 조회
db.numbers.countDocuments();
db.numbers_2.countDocuments();

