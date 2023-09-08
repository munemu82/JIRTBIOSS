const itemSchema = new mongoose.Schema({

owner : {
  type: mongoose.Schema.Types.ObjectId
   required: true,
   ref: 'User'
 },
 title: {
    type: String,
    required: true,
  },
  price: {
    type: Number,
    required: true,
  },
   description: {
    type: String,
    required: true,
  },
category: {
   type: String,
   required: true
},
  image: {
    type: String,
    required: true,
  },
rating: {
    rate: {
      type: Number,
      required: true,
      default: 0
    },
    count: {
      type: Number,
      required: true,
      default: 0
    },
  },
, {
timestamps: true
}
  )

const Item = mongoose.model("Item",itemSchema);

module.exports = Item;


// ADD Product
router.post('/items',Auth, async(req, res) => {
try {
const newItem = new Item({
    ...req.body,
    owner: req.user._id
})
   await newItem.save()
   res.status(201).send(newItem)
} catch (error) {
res.status(400).send({message: "error"})
}
})

//FETCH ONE 
    router.get('/items/:id', async(req, res) => {
try{
   const item = await Item.findOne({_id: req.params.id})
if(!item) {
   res.status(404).send({error: "Item not found"})
}
   res.status(200).send(item)
} catch (error) {
   res.status(400).send(error)
}
})

//Fetch all items
    router.get('/items', async(req, res) => {
try {
  const items = await Item.find({})
  res.status(200).send(items)
} catch (error) {
  res.status(400).send(error)
}
})

//UPDATE ITEM
    router.patch('/items/:id', Auth, async(req, res) => {
const updates = Object.keys(req.body)
const allowedUpdates = ['name', 'description', 'category', 'price']
const isValidOperation = updates.every((update) =>              allowedUpdates.includes(update))
   if(!isValidOperation) {
     return res.status(400).send({ error: 'invalid updates'})
}
try {
  const item = await Item.findOne({ _id: req.params.id})
  if(!item){
      return res.status(404).send()
  }
  updates.forEach((update) => item[update] = req.body[update])
  await item.save()
  res.send(item)
} catch (error) {
res.status(400).send(error)
}
})


-----------------------------------------
  TOP BEST AND FREE APIs
-----------------------------------------
  https://tripprivacy.com/free-apis-to-use-and-make-money/?expand_article=1
    
