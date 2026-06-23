/*
* 자바스크립트 셸을 통한 MongoDB (기본)
* */

use tutorial;

// pg2
db.users.insertOne({ username : "smith" });
db.users.insertOne({ username : "jones" });

db.users.find();
db.users.findOne();

// pg3
db.users.find({ username : "jones" });
db.users.find({
    $or : [
        {username : "jones"},
        {username : "smith" }
    ]
});

// pg4
db.users.updateOne({ username : "smith" }, {$set : {country: "Canada"}});
db.users.find();

// pg5
db.users.replaceOne({ username : "smith" }, {country: "Canada"});
db.users.find();

db.users.replaceOne({ country : "Canada" },
    {
        username: "smith" ,
        country: "Canada"
    }
);
db.users.find();

// pg6
db.users.updateOne({username : "smith"},
    {
        $unset : { country : "" }
    }
);
db.users.find();

// pg7
show dbs;
show collections;
db.stats();
db.users.stats();

// pg8
db.users.deleteOne({username : "smith"});
db.users.find();

db.users.deleteMany({});
db.users.find();

db.users.drop();



/*
* 쿼리 작성하기 (기본)
* */

// pg 10
use test;

for (let i=0; i<20000; i++){
    db.product.insertOne({
        num : i,
        name : "스마트폰 " + i
    });
}

db.product.countDocuments();

// pg 11
db.product.find().sort({num : -1});
db.product.find().sort({num : -1}).limit(10);
db.product.find().sort({ num: -1 }).skip(50).limit(10);

// pg 12
db.product.find({
    $or: [
        { num: { $lt: 15 } },
        { num: { $gt: 19995 } }
    ]
});

db.product.find({
        name :
            {
                $in: ['스마트폰 10', '스마트폰 100', '스마트폰 1000']
            }
    }
);

// pg 13
db.product.find(
    { num: { $lt: 5 } },
    { name : 1, _id : 0}
);