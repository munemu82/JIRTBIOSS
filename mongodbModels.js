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
